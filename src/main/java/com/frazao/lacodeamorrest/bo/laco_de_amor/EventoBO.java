package com.frazao.lacodeamorrest.bo.laco_de_amor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoDAO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoTipoDAO;
import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.ProdutoPrecoDestinacao;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Cotar;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Evento;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoPessoa;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoPessoaFuncao;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoProduto;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoTipo;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Pessoa;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.PessoaEndereco;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Produto;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoModelo;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.UnidadeMedida;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Vender;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventoBO extends CRUDBO<Evento, java.lang.Integer, EventoFiltroDTO, EventoDAO> {

	@PersistenceContext
	private EntityManager entityManager;

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
	public Evento entrando(@Valid final Evento t, @Valid final Evento anterior, final String acao) throws BOException {

		if (ObjectUtils.isEmpty(t.getEventoProdutoList())) {
			throw new BOException("Necessário informar produto(s)");
		}
		if (ObjectUtils.isEmpty(t.getEventoPessoaList())) {
			t.setEventoPessoaList(new ArrayList<>());
		}

		//////////////////
		// PRODUTO LIST //
		//////////////////

		final Map<Integer, Produto> pmSet = new HashMap<>();
		for (final EventoProduto ep : t.getEventoProdutoList()) {
			// o produto tem que ser informado com o seu modelo
			if (!ObjectUtils.allNotNull(ep.getProduto(), ep.getProduto().getProdutoModelo(),
					ep.getProduto().getProdutoModelo().getId())) {
				throw new BOException("Dados inconsistentes - informação de produto incompleta");
			}
			// verificar se está repetido
			if (pmSet.keySet().contains(ep.getProduto().getProdutoModelo().getId())) {
				if (t instanceof Cotar) {
					throw new BOException(String.format("Mesmo modelo de produto informado mais de uma vez! [%s]",
							ep.getProduto().getProdutoModelo().getCodigo()));
				}
				ep.setProduto(pmSet.get(ep.getProduto().getProdutoModelo().getId()));
			} else {
				pmSet.put(ep.getProduto().getProdutoModelo().getId(), ep.getProduto());
			}

			// pessoa vinculada ao produto
			if (ObjectUtils.isNotEmpty(ep.getEventoPessoa())) {
				if (t instanceof Cotar) {
					throw new BOException(String.format(
							"Composição de dados inválida! Informado Pessoa sem necessidade", ep.getEventoPessoa()));
				} else {
					if (!ObjectUtils.allNotNull(ep.getEventoPessoa().getPessoa(),
							ep.getEventoPessoa().getPessoa().getId())) {
						throw new BOException(
								String.format("Composição de dados inválida! Informação sobre a pessoa incompleta",
										ep.getEventoPessoa()));
					}
					if (!ObjectUtils.allNotNull(ep.getEventoPessoa().getEventoPessoaFuncao(),
							ep.getEventoPessoa().getEventoPessoaFuncao().getId())) {
						throw new BOException("Dados inconsistentes - função da pessoa incompleto");
					}
					if (!ObjectUtils.allNotNull(ep.getEventoPessoa().getEventoProdutoList())) {
						throw new BOException("Dados inconsistentes - relacao de produtos invalida");
					}
					boolean encontrou = false;
					for (final EventoPessoa eventoPessoa : t.getEventoPessoaList()) {
						if (eventoPessoa.getPessoa().getId().equals(ep.getEventoPessoa().getPessoa().getId())) {
							encontrou = true;
							break;
						}
					}
					if (!encontrou) {
						final EventoPessoa epes = new EventoPessoa();
						epes.setEventoPessoaFuncao(ep.getEventoPessoa().getEventoPessoaFuncao());
						epes.setPessoa(ep.getEventoPessoa().getPessoa());
						t.getEventoPessoaList().add(epes);
					}
					ep.getEventoPessoa().setEvento(null);
				}
			}
			// atribuir o link ao evento
			ep.setEvento(t);
			// calcular valor total
			if (ObjectUtils.allNotNull(ep.getQuantidade(), ep.getValorUnitario())) {
				ep.setValorTotal(ep.getQuantidade().multiply(ep.getValorUnitario()));
			} else {
				ep.setValorTotal(null);
			}
		}

		/////////////////
		// PESSOA LIST //
		/////////////////

		final Set<Integer> excluiSet = new HashSet<>();
		final Set<Integer> peSet = new HashSet<>();
		if (t.getEventoPessoaList().size() > 0) {
			for (final EventoPessoa ep : t.getEventoPessoaList()) {
				// validar pessoa
				if (!ObjectUtils.allNotNull(ep.getPessoa(), ep.getPessoa().getId())) {
					throw new BOException("Dados inconsistentes - pessoa inválida");
				}
				if (!ObjectUtils.allNotNull(ep.getEventoPessoaFuncao(), ep.getEventoPessoaFuncao().getId())) {
					throw new BOException("Dados inconsistentes - função da pessoa incompleto");
				}
				if (peSet.contains(ep.getPessoa().getId())) {
					throw new BOException(
							String.format("Mesmo pessoa informada mais de uma vez! [%s]", ep.getPessoa().getNome()));
				} else {
					peSet.add(ep.getPessoa().getId());
				}
				boolean encontrou = false;
				for (final EventoProduto eprod : t.getEventoProdutoList()) {
					if (eprod.getEventoPessoa() != null
							&& eprod.getEventoPessoa().getPessoa().getId().equals(ep.getPessoa().getId())) {
						encontrou = true;
						break;
					}
				}
				if (!encontrou) {
					if (!(t instanceof Cotar) && !(t instanceof Vender)) {
						excluiSet.add(ep.getId());
						continue;
					}
				}
				if (ObjectUtils.isNotEmpty(ep.getEventoProdutoList())) {
					for (final EventoProduto epp : ep.getEventoProdutoList()) {
						if (!ObjectUtils.allNotNull(epp.getProduto(), epp.getProduto().getProdutoModelo(),
								epp.getProduto().getProdutoModelo().getId())) {
							throw new BOException(
									"Dados inconsistentes - informação de produto vinculado a pessoa incompleto");
						}
						if (!pmSet.keySet().contains(epp.getProduto().getProdutoModelo().getId())) {
							if (!(t instanceof Cotar)) {
								throw new BOException("Modelo de produto não relacionado!");
							}
						}
						if (ObjectUtils.isNotEmpty(epp.getEvento())) {
							throw new BOException("Dados inconsistentes");
						}
						epp.setEvento(null);
						epp.setEventoPessoa(ep);
						epp.setProduto(pmSet.get(epp.getProduto().getProdutoModelo().getId()));
						if (ObjectUtils.isEmpty(epp.getProduto())) {
							throw new BOException("Dados inconsistentes - produto");
						}
						// calcular valor total
						if (ObjectUtils.allNotNull(epp.getQuantidade(), epp.getValorUnitario())) {
							epp.setValorTotal(epp.getQuantidade().multiply(epp.getValorUnitario()));
						} else {
							epp.setValorTotal(null);
						}
					}
				}

				// atribuir o link ao evento
				ep.setEvento(t);
			}
		}

		// excluir itens orfaos
		if (!ObjectUtils.isEmpty(excluiSet)) {
			for (int k = t.getEventoPessoaList().size() - 1; k >= 0; k--) {
				if (excluiSet.contains(((List<EventoPessoa>) t.getEventoPessoaList()).get(k).getId())) {
					((List<EventoPessoa>) t.getEventoPessoaList()).remove(k);
				}
			}
		}

		// criar produtos não salvos
		for (final Produto prd : pmSet.values()) {
			if (prd.getId() == null) {
				EventoBO.log.debug("Criando novo produto - modelo = {}", prd.getProdutoModelo());
				final Integer id = this.produtoBO.create(prd);
				prd.setId(id);
			}
		}

		if (anterior != null) {
			// excluir registros órfãos
			Collection<Integer> idsOrfaos = this.idsOrfaos(anterior.getEventoProdutoList(), t.getEventoProdutoList());
			if (ObjectUtils.isNotEmpty(idsOrfaos.toArray())) {
				EventoBO.log.debug("Excluindo produtos órfãos {}", idsOrfaos);
				this.eventoProdutoBO.delete(idsOrfaos);
			}

			// excluir registros órfãos
			idsOrfaos = this.idsOrfaos(anterior.getEventoPessoaList(), t.getEventoPessoaList());
			if (ObjectUtils.isNotEmpty(idsOrfaos.toArray())) {
				EventoBO.log.debug("Excluindo pessoas órfãos {}", idsOrfaos);
				this.eventoPessoaBO.delete(idsOrfaos);
			}
		}

		return t;
	}

	@Override
	public Evento entrou(@Valid final Evento t, final String acao) throws BOException {
		this.entityManager.detach(t);

		this.salvarEventoPessoa(t.getEventoPessoaList());
		this.salvarEventoProduto(t.getEventoProdutoList());

		return t;
	}

	@Override
	public void excluindo(final Evento anterior, final Integer id) throws BOException {
		if (anterior.getEventoProdutoList() != null && anterior.getEventoProdutoList().size() > 0) {
			this.eventoProdutoBO.delete(this.idList(anterior.getEventoProdutoList()));
		}
		if (anterior.getEventoPessoaList() != null && anterior.getEventoPessoaList().size() > 0) {
			this.eventoPessoaBO.delete(this.idList(anterior.getEventoPessoaList()));
		}
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

	private EventoPessoa saiEventoPessoa(final EventoPessoa eventoPessoa, final int nivel) {
		EventoPessoa result = null;
		if (eventoPessoa != null && nivel <= 2) {
			result = new EventoPessoa(eventoPessoa.getId());
			result.setPessoa(this.saiPessoa(eventoPessoa.getPessoa()));
			result.setEventoPessoaFuncao(new EventoPessoaFuncao(eventoPessoa.getEventoPessoaFuncao().getId()));
			result.setEventoProdutoList(this.saiEventoProdutoList(eventoPessoa.getEventoProdutoList(), nivel));
		}
		return result;
	}

	private Collection<EventoPessoa> saiEventoPessoaList(final Collection<EventoPessoa> eventoPessoaList, int nivel) {
		final List<EventoPessoa> result = new ArrayList<>();
		if (eventoPessoaList != null && eventoPessoaList.size() > 0 && nivel++ < 2) {
			for (int i = 0; i < eventoPessoaList.size(); i++) {
				final EventoPessoa ep = ((List<EventoPessoa>) eventoPessoaList).get(i);
				result.add(this.saiEventoPessoa(ep, nivel));
			}
		}
		return result;
	}

	private Collection<EventoProduto> saiEventoProdutoList(final Collection<EventoProduto> lista, int nivel) {
		final List<EventoProduto> result = new ArrayList<>();
		if (lista != null && nivel++ < 2) {
			for (int i = 0; i < lista.size(); i++) {
				final EventoProduto ep = ((List<EventoProduto>) lista).get(i);

				final EventoProduto e = new EventoProduto(ep.getId());
				e.setProduto(this.saiProduto(ep.getProduto()));
				e.setQuantidade(ep.getQuantidade());
				e.setUnidadeMedida(this.saiUnidadeMedida(ep.getUnidadeMedida()));
				e.setValorUnitario(ep.getValorUnitario());
				e.setValorTotal(ep.getValorTotal());
				e.setEventoPessoa(this.saiEventoPessoa(ep.getEventoPessoa(), nivel));
				result.add(e);
			}
		}
		return result;
	}

	private Pessoa saiPessoa(final Pessoa pessoa) {
		final Pessoa result = new Pessoa(pessoa.getId());
		result.setNome(pessoa.getNome());
		result.setCpfCnpj(pessoa.getCpfCnpj());
		result.setEmail(pessoa.getEmail());
		result.setContato1(pessoa.getContato1());
		result.setContato2(pessoa.getContato2());
		result.setContato3(pessoa.getContato3());
		final List<PessoaEndereco> pessoaEnderecoList = new ArrayList<>();
		if (pessoa.getPessoaEnderecoList() != null) {
			pessoa.getPessoaEnderecoList().forEach(e -> pessoaEnderecoList.add(e));
		}
		result.setPessoaEnderecoList(pessoaEnderecoList);
		result.setPessoaTipo(pessoa.getPessoaTipo());
		return result;
	}

	private Produto saiProduto(final Produto produto) {
		final Produto result = new Produto(produto.getId());
		final ProdutoModelo pm = new ProdutoModelo(produto.getProdutoModelo().getId());
		pm.setCodigo(produto.getProdutoModelo().getCodigo());
		pm.setNome(produto.getProdutoModelo().getNome());
		pm.setFoto(produto.getProdutoModelo().getFoto());
		produto.getProdutoModelo().getProdutoDescricaoList().stream()
				.sorted((a, b) -> a.getOrdem().compareTo(b.getOrdem()))
				.forEach(v -> pm.getProdutoDescricaoList().add(v));
		produto.getProdutoModelo().getProdutoPrecoList().stream()
				.sorted((a, b) -> b.getVigencia().compareTo(a.getVigencia()))
				.filter(v -> ProdutoPrecoDestinacao.Compra.equals(v.getDestinacao())
						&& (v.getVigencia().equals(LocalDate.now()) || v.getVigencia().isBefore(LocalDate.now())))
				.findFirst().ifPresent(v -> pm.getProdutoPrecoList().add(v));
		produto.getProdutoModelo().getProdutoPrecoList().stream()
				.sorted((a, b) -> b.getVigencia().compareTo(a.getVigencia()))
				.filter(v -> ProdutoPrecoDestinacao.Venda.equals(v.getDestinacao())
						&& (v.getVigencia().equals(LocalDate.now()) || v.getVigencia().isBefore(LocalDate.now())))
				.findFirst().ifPresent(v -> pm.getProdutoPrecoList().add(v));
		result.setProdutoModelo(pm);
		return result;
	}

	@Override
	public Evento saiu(@Valid final Evento t, final String acao) throws BOException {
		if (t == null) {
			return t;
		}

		try {
			final Evento result = (Evento) Hibernate.getClass(t).newInstance();
			result.setId(t.getId());
			result.setEventoTipo(new EventoTipo(t.getEventoTipo().getId()));
			result.setData(t.getData());
			result.setPaiId(t.getPaiId());
			result.setPai(this.saiu(t.getPai(), acao));
			result.setEventoPessoaList(this.saiEventoPessoaList(t.getEventoPessoaList(), 0));
			result.setEventoProdutoList(this.saiEventoProdutoList(t.getEventoProdutoList(), 0));
			if (t instanceof Vender) {
				((Vender) result).setEndereco(((Vender) t).getEndereco());
			}
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BOException(e);
		}
	}

	private UnidadeMedida saiUnidadeMedida(final UnidadeMedida unidadeMedida) {
		final UnidadeMedida result = new UnidadeMedida(unidadeMedida.getId());
		result.setNome(unidadeMedida.getNome());
		result.setCodigo(unidadeMedida.getCodigo());
		return result;
	}

	private void salvarEventoPessoa(final Collection<EventoPessoa> lista) throws BOException {
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				final EventoPessoa pe = ((List<EventoPessoa>) lista).get(i);
				if (pe.getId() == null) {
					this.eventoPessoaBO.create(pe);
				} else {
					this.eventoPessoaBO.update(pe.getId(), pe);
				}
				entityManager.detach(pe);
				this.salvarEventoProduto(pe.getEventoProdutoList());
			}
		}
	}

	private void salvarEventoProduto(final Collection<EventoProduto> lista) throws BOException {
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				final EventoProduto pe = ((List<EventoProduto>) lista).get(i);
				if (pe.getEventoPessoa() != null) {
					if (pe.getEventoPessoa().getId() == null) {
						this.eventoPessoaBO.create(pe.getEventoPessoa());
					} else {
						this.eventoPessoaBO.update(pe.getEventoPessoa().getId(), pe.getEventoPessoa());
					}
				}
				if (pe.getId() == null) {
					this.eventoProdutoBO.create(pe);
				} else {
					this.eventoProdutoBO.update(pe.getId(), pe);
				}
			}
		}
	}

}
