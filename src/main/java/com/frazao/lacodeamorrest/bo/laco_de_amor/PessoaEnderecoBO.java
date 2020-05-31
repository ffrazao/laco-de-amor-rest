package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.PessoaEnderecoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.PessoaEnderecoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.PessoaEndereco;

@Service
public class PessoaEnderecoBO
		extends CRUDBO<PessoaEndereco, java.lang.Integer, PessoaEnderecoFiltroDTO, PessoaEnderecoDAO> {

	public PessoaEnderecoBO(@Autowired final PessoaEnderecoDAO dao) {
		super(PessoaEndereco.class, dao);
	}

}
