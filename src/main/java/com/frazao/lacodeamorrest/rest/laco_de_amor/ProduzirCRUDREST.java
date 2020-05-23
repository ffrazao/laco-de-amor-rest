package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.ProduzirBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProduzirFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Produzir;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "produzir")
public class ProduzirCRUDREST extends CRUDREST<Produzir, Integer, ProduzirFiltroDTO, ProduzirBO> {

	public ProduzirCRUDREST(@Autowired final ProduzirBO bo) {
		super(bo);
	}

}
