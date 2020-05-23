package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.EventoPessoaBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoPessoaFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoPessoa;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "evento-pessoa")
public class EventoPessoaCRUDREST
		extends CRUDREST<EventoPessoa, java.lang.Integer, EventoPessoaFiltroDTO, EventoPessoaBO> {

	public EventoPessoaCRUDREST(@Autowired final EventoPessoaBO bo) {
		super(bo);
	}

}
