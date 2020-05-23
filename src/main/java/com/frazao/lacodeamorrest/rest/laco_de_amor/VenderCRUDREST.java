package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.VenderBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.VenderFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Vender;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "vender")
public class VenderCRUDREST extends CRUDREST<Vender, Integer, VenderFiltroDTO, VenderBO> {

	public VenderCRUDREST(@Autowired final VenderBO bo) {
		super(bo);
	}

}
