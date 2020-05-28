package com.frazao.lacodeamorrest.bo.laco_de_amor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.VenderDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.VenderFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Vender;

@Service
public class VenderBO extends CRUDBO<Vender, Integer, VenderFiltroDTO> {

	@Autowired
	private EventoBO eventoBO;

	public VenderBO(@Autowired final VenderDAO dao) {
		super(Vender.class, dao);
	}

	@Override
	public Vender entrando(@Valid final Vender t, final String acao) throws BOException {
		final Vender result = (Vender) this.eventoBO.entrando(t, acao);
		return result;
	}

	@Override
	public Vender entrou(@Valid final Vender t, final String acao) throws BOException {
		final Vender result = (Vender) this.eventoBO.entrou(t, acao);
		return result;
	}

	@Override
	public Vender novo(final Vender modelo) throws BOException {
		final Vender result = (Vender) this.eventoBO.novo(super.novo(modelo));
		return result;
	}

	@Override
	public Integer saindo(@Valid final Integer id, final String acao) throws BOException {
		final Integer result = this.eventoBO.saindo(id, acao);
		return result;
	}

	@Override
	public Vender saiu(@Valid final Vender t, final String acao) throws BOException {
		final Vender result = (Vender) this.eventoBO.saiu(t, acao);
		return result;
	}

}
