package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.PessoaDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.PessoaFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Pessoa;

@Service
public class PessoaBO extends CRUDBO<Pessoa, Integer, PessoaFiltroDTO> {

	private static final Logger LOG = LoggerFactory.getLogger(PessoaBO.class);

	public PessoaBO(@Autowired PessoaDAO dao) {
		super(dao);
	} 

	public PessoaDAO getDAO() {
		return (PessoaDAO) super.getDAO();
	}

}
