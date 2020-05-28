package com.frazao.lacodeamorrest.bo.laco_de_amor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoDAO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoTipoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Evento;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoPessoa;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoProduto;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Pessoa;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Produto;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoModelo;

@Service
public class EventoBO extends CRUDBO<Evento, java.lang.Integer, EventoFiltroDTO> {

	@Autowired
	private EventoPessoaBO eventoPessoaBO;

	@Autowired
	private EventoProdutoBO eventoProdutoBO;

	@Autowired
	private EventoTipoDAO eventoTipoDAO;

	@Autowired
	private ProdutoBO produtoBO;

	public EventoBO(@Autowired final EventoDAO dao) {
		super(Evento.class, dao);
	}

	@Override
	public Evento entrando(@Valid final Evento t, final String acao) throws BOException {
		if (ObjectUtils.isEmpty(t.getEventoProdutoList())) {
			throw new BOException("Necessário informar produto(s)");
		}
		// ajustar os produtos do evento
		final Map<ProdutoModelo, Produto> pmSet = new HashMap<>();
		for (final EventoProduto ep : t.getEventoProdutoList()) {
			if (!ObjectUtils.allNotNull(ep.getProduto(), ep.getProduto().getProdutoModelo(),
					ep.getProduto().getProdutoModelo().getId())) {
				throw new BOException("Dados inconsistentes");
			}
			if (pmSet.keySet().contains(ep.getProduto().getProdutoModelo())) {
				throw new BOException("Mesmo modelo de produto informado mais de uma vez!");
			}
			pmSet.put(ep.getProduto().getProdutoModelo(), ep.getProduto());

			if (ObjectUtils.isNotEmpty(ep.getEventoPessoa())) {
				throw new BOException("Dados inconsistentes");
			}
			// atribuir o link ao evento
			ep.setEvento(t);
			if (ObjectUtils.allNotNull(ep.getQuantidade(), ep.getValorUnitario())) {
				ep.setValorTotal(ep.getQuantidade().multiply(ep.getValorUnitario()));
			}
		}

		final Set<Pessoa> peSet = new HashSet<>();
		if (t.getEventoPessoaList().size() > 0) {
			for (final EventoPessoa ep : t.getEventoPessoaList()) {
				if (!ObjectUtils.allNotNull(ep.getPessoa(), ep.getPessoa().getId())) {
					throw new BOException("Dados inconsistentes");
				}
				if (peSet.contains(ep.getPessoa())) {
					throw new BOException("Mesmo pessoa informada mais de uma vez!");
				}
				peSet.add(ep.getPessoa());

				for (final EventoProduto epp : ep.getEventoProdutoList()) {
					if (!ObjectUtils.allNotNull(epp.getProduto(), epp.getProduto().getProdutoModelo(),
							epp.getProduto().getProdutoModelo().getId())) {
						throw new BOException("Dados inconsistentes");
					}
					if (!pmSet.keySet().contains(epp.getProduto().getProdutoModelo())) {
						throw new BOException("Modelo de produto não relacionado!");
					}
					if (ObjectUtils.isNotEmpty(epp.getEvento())) {
						throw new BOException("Dados inconsistentes");
					}
					epp.setEventoPessoa(ep);
					epp.setProduto(pmSet.get(epp.getProduto().getProdutoModelo()));
					if (ObjectUtils.allNotNull(epp.getQuantidade(), epp.getValorUnitario())) {
						epp.setValorTotal(epp.getQuantidade().multiply(epp.getValorUnitario()));
					}
				}

				// atribuir o link ao evento
				ep.setEvento(t);
			}
		}

		// criar produtos não salvos
		for (final Produto prd : pmSet.values()) {
			if (prd.getId() == null) {
				this.produtoBO.create(prd);
			}
		}

		return t;
	}

	@Override
	public Evento entrou(@Valid final Evento t, final String acao) throws BOException {

		this.salvarEventoProduto(t.getEventoProdutoList());
		this.salvarEventoPessoa(t.getEventoPessoaList());

		return t;
	}

	@Override
	public Evento novo(final Evento modelo) throws BOException {
		final Evento result = super.novo(modelo);
		try {
			result.setEventoTipo(
					this.eventoTipoDAO.getFindByCodigo((String) modelo.getClass().getField("CODIGO").get(modelo)));
		} catch (final Exception e) {
			System.exit(0);
		}
		result.setData(LocalDateTime.now());
		return result;
	}

	private void saiEventoProdutoList(final List<EventoProduto> eventoProdutoList) {
		for (final EventoProduto e : eventoProdutoList) {
			if (e.getEventoPessoa() != null) {
				e.getEventoPessoa().setPessoa(new Pessoa(e.getEventoPessoa().getPessoa().getId()));
//				this.saiEventoProdutoList(e.getEventoPessoa().getEventoProdutoList());
			}
		}
	}

	@Override
	public Evento saiu(@Valid final Evento t, final String acao) throws BOException {
		final Evento result = super.saiu(t, acao);
//		if (result.getEventoPessoaList().size() > 0) {
//			for (final EventoPessoa e : result.getEventoPessoaList()) {
//				this.saiEventoProdutoList(e.getEventoProdutoList());
//			}
//		}
//		if (result.getEventoProdutoList().size() > 0) {
//			this.saiEventoProdutoList(result.getEventoProdutoList());
//		}
		return result;
	}

	private void salvarEventoPessoa(final List<EventoPessoa> lista) throws BOException {
		for (final EventoPessoa pe : lista) {
			if (pe.getId() == null) {
				this.eventoPessoaBO.create(pe);
			} else {
				this.eventoPessoaBO.update(pe.getId(), pe);
			}
			this.salvarEventoProduto(pe.getEventoProdutoList());
		}
	}

	private void salvarEventoProduto(final List<EventoProduto> lista) throws BOException {
		for (final EventoProduto pe : lista) {
			if (pe.getId() == null) {
				this.eventoProdutoBO.create(pe);
			} else {
				this.eventoProdutoBO.update(pe.getId(), pe);
			}
		}
	}

}
