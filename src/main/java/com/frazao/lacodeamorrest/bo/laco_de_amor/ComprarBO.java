package com.frazao.lacodeamorrest.bo.laco_de_amor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ComprarDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ComprarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Comprar;

@Service
public class ComprarBO extends CRUDBO<Comprar, Integer, ComprarFiltroDTO> {

	@Autowired
	private EventoBO eventoBO;

	public ComprarBO(@Autowired final ComprarDAO dao) {
		super(Comprar.class, dao);
	}

	@Override
	public Comprar entrando(@Valid final Comprar t, final String acao) throws BOException {
		final Comprar result = (Comprar) this.eventoBO.entrando(t, acao);
		return result;
	}

	@Override
	public Comprar entrou(@Valid final Comprar t, final String acao) throws BOException {
		final Comprar result = (Comprar) this.eventoBO.entrou(t, acao);
		return result;
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
