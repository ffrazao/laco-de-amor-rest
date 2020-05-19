package com.frazao.lacodeamorrest.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.dao.PessoaDAO;
import com.frazao.lacodeamorrest.modelo.dto.PessoaFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.Pessoa;

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
