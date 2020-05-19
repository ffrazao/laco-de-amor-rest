package com.frazao.lacodeamorrest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.PessoaBO;
import com.frazao.lacodeamorrest.modelo.dto.PessoaFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.Pessoa;

@RestController
@RequestMapping(value = "pessoa")
public class PessoaCRUDREST extends CRUDREST<Pessoa, Integer, PessoaFiltroDTO, PessoaBO> {

	public PessoaCRUDREST(@Autowired PessoaBO bo) {
		super(bo);
	}

}
