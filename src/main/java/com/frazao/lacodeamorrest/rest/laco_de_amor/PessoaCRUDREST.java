package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.PessoaBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.PessoaFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Pessoa;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "pessoa")
public class PessoaCRUDREST extends CRUDREST<Pessoa, Integer, PessoaFiltroDTO, PessoaBO> {

	public PessoaCRUDREST(@Autowired PessoaBO bo) {
		super(bo);
	}

	@Override
	public Pessoa novo(Pessoa modelo) throws Exception {
		return modelo == null ? new Pessoa() : modelo;
	}

}
