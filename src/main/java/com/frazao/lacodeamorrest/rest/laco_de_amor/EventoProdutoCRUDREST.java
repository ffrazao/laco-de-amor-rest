package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.EventoProdutoBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoProdutoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoProduto;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "evento-produto")
public class EventoProdutoCRUDREST
		extends CRUDREST<EventoProduto, java.lang.Integer, EventoProdutoFiltroDTO, EventoProdutoBO> {

	public EventoProdutoCRUDREST(@Autowired final EventoProdutoBO bo) {
		super(bo);
	}

}
