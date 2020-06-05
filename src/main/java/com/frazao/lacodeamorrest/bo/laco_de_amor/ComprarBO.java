package com.frazao.lacodeamorrest.bo.laco_de_amor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ComprarDAO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoModeloDAO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoPrecoDAO;
import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.ProdutoPrecoDestinacao;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ComprarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Comprar;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoProduto;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoModelo;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoPreco;

@Service
public class ComprarBO extends CRUDBO<Comprar, Integer, ComprarFiltroDTO, ComprarDAO> {

	@Autowired
	private EventoBO eventoBO;

	@Autowired
	private ProdutoModeloDAO produtoModeloDAO;

	@Autowired
	private ProdutoPrecoDAO produtoPrecoDAO;

	public ComprarBO(@Autowired final ComprarDAO dao) {
		super(Comprar.class, dao);
	}

	@Override
	public Comprar entrando(@Valid final Comprar t, @Valid final Comprar anterior, final String acao)
			throws BOException {
		final Comprar result = (Comprar) this.eventoBO.entrando(t, anterior, acao);
		return result;
	}

	@Override
	public Comprar entrou(@Valid final Comprar t, final String acao) throws BOException {
		final Comprar result = (Comprar) this.eventoBO.entrou(t, acao);
		
		// atualizar valor de venda
		for (EventoProduto eventoProduto: result.getEventoProdutoList()) {
			ProdutoModelo produtoModelo = produtoModeloDAO.getOne(eventoProduto.getProduto().getProdutoModelo().getId());
			if (produtoModelo.getProdutoPrecoList() == null) {
				produtoModelo.setProdutoPrecoList(new ArrayList<>());
			}
			List<ProdutoPreco> produtoPrecoList = (List<ProdutoPreco>) produtoModelo.getProdutoPrecoList();
			
			LocalDate data = result.getData().toLocalDate();
			ProdutoPreco produtoPreco = null;
			for (ProdutoPreco prodPreco: produtoPrecoList) {
				if (ProdutoPrecoDestinacao.Compra.equals(prodPreco.getDestinacao()) && prodPreco.getVigencia().equals(data)) {
					produtoPreco = prodPreco;
					break;
				}
			}
			if (produtoPreco == null) {
				produtoPreco = new ProdutoPreco();
				produtoPreco.setVigencia(data);
				produtoPreco.setDestinacao(ProdutoPrecoDestinacao.Compra);
				produtoPreco.setProdutoModelo(produtoModelo);
			}
			produtoPreco.setValor(eventoProduto.getValorUnitario());
			
			produtoPrecoDAO.save(produtoPreco);
		}
		
		return result;
	}

	@Override
	public void excluindo(final Comprar anterior, final Integer id) throws BOException {
		this.eventoBO.excluindo(anterior, id);
	}

	@Override
	public Comprar novo(final Comprar modelo) throws BOException {
		final Comprar result = (Comprar) this.eventoBO.novo(super.novo(modelo));
		return result;
	}

	@Override
	public Integer saindo(@Valid final Integer id, final String acao) throws BOException {
		final Integer result = this.eventoBO.saindo(id, acao);
		return result;
	}

	@Override
	public Comprar saiu(@Valid final Comprar t, final String acao) throws BOException {
		final Comprar result = (Comprar) this.eventoBO.saiu(t, acao);
		return result;
	}

}
