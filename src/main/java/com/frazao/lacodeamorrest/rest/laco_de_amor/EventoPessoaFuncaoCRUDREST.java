package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.EventoPessoaFuncaoBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoPessoaFuncaoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoPessoaFuncao;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "evento-pessoa-funcao")
public class EventoPessoaFuncaoCRUDREST
		extends CRUDREST<EventoPessoaFuncao, java.lang.Integer, EventoPessoaFuncaoFiltroDTO, EventoPessoaFuncaoBO> {

	public EventoPessoaFuncaoCRUDREST(@Autowired final EventoPessoaFuncaoBO bo) {
		super(bo);
	}

}
