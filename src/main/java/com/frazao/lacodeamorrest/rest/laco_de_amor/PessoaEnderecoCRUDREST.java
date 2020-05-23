package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.PessoaEnderecoBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.PessoaEnderecoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.PessoaEndereco;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "pessoa-endereco")
public class PessoaEnderecoCRUDREST
		extends CRUDREST<PessoaEndereco, java.lang.Integer, PessoaEnderecoFiltroDTO, PessoaEnderecoBO> {

	public PessoaEnderecoCRUDREST(@Autowired final PessoaEnderecoBO bo) {
		super(bo);
	}

}
