package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.CotarBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.CotarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Cotar;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "cotar")
public class CotarCRUDREST extends CRUDREST<Cotar, Integer, CotarFiltroDTO, CotarBO> {

	public CotarCRUDREST(@Autowired final CotarBO bo) {
		super(bo);
	}

}
