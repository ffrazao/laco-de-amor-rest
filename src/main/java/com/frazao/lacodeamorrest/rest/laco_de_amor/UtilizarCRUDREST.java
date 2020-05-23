package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.UtilizarBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.UtilizarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Utilizar;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "utilizar")
public class UtilizarCRUDREST extends CRUDREST<Utilizar, Integer, UtilizarFiltroDTO, UtilizarBO> {

	public UtilizarCRUDREST(@Autowired final UtilizarBO bo) {
		super(bo);
	}

}
