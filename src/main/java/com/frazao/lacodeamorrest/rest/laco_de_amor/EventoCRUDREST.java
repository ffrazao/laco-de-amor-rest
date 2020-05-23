package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.EventoBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Evento;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "evento")
public class EventoCRUDREST extends CRUDREST<Evento, java.lang.Integer, EventoFiltroDTO, EventoBO> {

	public EventoCRUDREST(@Autowired final EventoBO bo) {
		super(bo);
	}

}
