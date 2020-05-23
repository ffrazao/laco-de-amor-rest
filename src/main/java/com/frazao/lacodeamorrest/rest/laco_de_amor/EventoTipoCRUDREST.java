package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.EventoTipoBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoTipoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoTipo;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "evento-tipo")
public class EventoTipoCRUDREST extends CRUDREST<EventoTipo, java.lang.Integer, EventoTipoFiltroDTO, EventoTipoBO> {

	public EventoTipoCRUDREST(@Autowired final EventoTipoBO bo) {
		super(bo);
	}

}
