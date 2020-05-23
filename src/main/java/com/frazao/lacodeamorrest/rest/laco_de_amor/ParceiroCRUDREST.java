package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.ParceiroBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ParceiroFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Parceiro;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "parceiro")
public class ParceiroCRUDREST extends CRUDREST<Parceiro, Integer, ParceiroFiltroDTO, ParceiroBO> {

	public ParceiroCRUDREST(@Autowired final ParceiroBO bo) {
		super(bo);
	}

}
