package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Evento;

@Service
public class EventoBO extends CRUDBO<Evento, java.lang.Integer, EventoFiltroDTO> {

	public EventoBO(@Autowired final EventoDAO dao) {
		super(Evento.class, dao);
	}

	@Override
	public EventoDAO getDAO() {
		return (EventoDAO) super.getDAO();
	}

}
