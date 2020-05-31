package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoTipoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoTipoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoTipo;

@Service
public class EventoTipoBO extends CRUDBO<EventoTipo, java.lang.Integer, EventoTipoFiltroDTO, EventoTipoDAO> {

	public EventoTipoBO(@Autowired final EventoTipoDAO dao) {
		super(EventoTipo.class, dao);
	}

}
