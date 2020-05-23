package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.EnderecoBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EnderecoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Endereco;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "endereco")
public class EnderecoCRUDREST extends CRUDREST<Endereco, java.lang.Integer, EnderecoFiltroDTO, EnderecoBO> {

	public EnderecoCRUDREST(@Autowired final EnderecoBO bo) {
		super(bo);
	}

}
