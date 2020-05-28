package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.PessoaDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.PessoaFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Pessoa;

@Service
public class PessoaBO extends CRUDBO<Pessoa, Integer, PessoaFiltroDTO> {

	public PessoaBO(@Autowired final PessoaDAO dao) {
		super(Pessoa.class, dao);
	}

	@Override
	public PessoaDAO getDAO() {
		return (PessoaDAO) super.getDAO();
	}

}
