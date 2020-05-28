package com.frazao.lacodeamorrest.bo.laco_de_amor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.UtilizarDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.UtilizarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Utilizar;

@Service
public class UtilizarBO extends CRUDBO<Utilizar, Integer, UtilizarFiltroDTO> {

	@Autowired
	private EventoBO eventoBO;

	public UtilizarBO(@Autowired final UtilizarDAO dao) {
		super(Utilizar.class, dao);
	}

	@Override
	public Utilizar entrando(@Valid final Utilizar t, final String acao) throws BOException {
		final Utilizar result = (Utilizar) this.eventoBO.entrando(t, acao);
		return result;
	}

	@Override
	public Utilizar entrou(@Valid final Utilizar t, final String acao) throws BOException {
		final Utilizar result = (Utilizar) this.eventoBO.entrou(t, acao);
		return result;
	}

	@Override
	public Utilizar novo(final Utilizar modelo) throws BOException {
		final Utilizar result = (Utilizar) this.eventoBO.novo(super.novo(modelo));
		return result;
	}

	@Override
	public Integer saindo(@Valid final Integer id, final String acao) throws BOException {
		final Integer result = this.eventoBO.saindo(id, acao);
		return result;
	}

	@Override
	public Utilizar saiu(@Valid final Utilizar t, final String acao) throws BOException {
		final Utilizar result = (Utilizar) this.eventoBO.saiu(t, acao);
		return result;
	}

}
