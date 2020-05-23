package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.ClienteBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ClienteFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Cliente;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "cliente")
public class ClienteCRUDREST extends CRUDREST<Cliente, Integer, ClienteFiltroDTO, ClienteBO> {

	public ClienteCRUDREST(@Autowired final ClienteBO bo) {
		super(bo);
	}

}
