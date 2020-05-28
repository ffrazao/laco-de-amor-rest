package com.frazao.lacodeamorrest.bo.laco_de_amor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.CotarDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.CotarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Cotar;

@Service
public class CotarBO extends CRUDBO<Cotar, Integer, CotarFiltroDTO> {

	@Autowired
	private EventoBO eventoBO;

	public CotarBO(@Autowired final CotarDAO dao) {
		super(Cotar.class, dao);
	}

	@Override
	public Cotar entrando(@Valid final Cotar t, final String acao) throws BOException {
		final Cotar result = (Cotar) this.eventoBO.entrando(t, acao);
		return result;
	}

	@Override
	public Cotar entrou(@Valid final Cotar t, final String acao) throws BOException {
		final Cotar result = (Cotar) this.eventoBO.entrou(t, acao);
		return result;
	}

	@Override
	public Cotar novo(final Cotar modelo) throws BOException {
		final Cotar result = (Cotar) this.eventoBO.novo(super.novo(modelo));
		return result;
	}

	@Override
	public Integer saindo(@Valid final Integer id, final String acao) throws BOException {
		final Integer result = this.eventoBO.saindo(id, acao);
		return result;
	}

	@Override
	public Cotar saiu(@Valid final Cotar t, final String acao) throws BOException {
		final Cotar result = (Cotar) this.eventoBO.saiu(t, acao);
		return result;
	}

}
