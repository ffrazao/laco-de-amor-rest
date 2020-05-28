package com.frazao.lacodeamorrest.bo.laco_de_amor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProduzirDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProduzirFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Produzir;

@Service
public class ProduzirBO extends CRUDBO<Produzir, Integer, ProduzirFiltroDTO> {

	@Autowired
	private EventoBO eventoBO;

	public ProduzirBO(@Autowired final ProduzirDAO dao) {
		super(Produzir.class, dao);
	}

	@Override
	public Produzir entrando(@Valid final Produzir t, final String acao) throws BOException {
		final Produzir result = (Produzir) this.eventoBO.entrando(t, acao);
		return result;
	}

	@Override
	public Produzir entrou(@Valid final Produzir t, final String acao) throws BOException {
		final Produzir result = (Produzir) this.eventoBO.entrou(t, acao);
		return result;
	}

	@Override
	public Produzir novo(final Produzir modelo) throws BOException {
		final Produzir result = (Produzir) this.eventoBO.novo(super.novo(modelo));
		return result;
	}

	@Override
	public Integer saindo(@Valid final Integer id, final String acao) throws BOException {
		final Integer result = this.eventoBO.saindo(id, acao);
		return result;
	}

	@Override
	public Produzir saiu(@Valid final Produzir t, final String acao) throws BOException {
		final Produzir result = (Produzir) this.eventoBO.saiu(t, acao);
		return result;
	}

}
