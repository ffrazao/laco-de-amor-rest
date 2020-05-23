package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.ComprarBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ComprarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Comprar;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "comprar")
public class ComprarCRUDREST extends CRUDREST<Comprar, Integer, ComprarFiltroDTO, ComprarBO> {

	public ComprarCRUDREST(@Autowired final ComprarBO bo) {
		super(bo);
	}

}
