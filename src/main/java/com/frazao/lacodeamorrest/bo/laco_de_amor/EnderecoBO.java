package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EnderecoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EnderecoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Endereco;

@Service
public class EnderecoBO extends CRUDBO<Endereco, Integer, EnderecoFiltroDTO, EnderecoDAO> {

	public EnderecoBO(@Autowired final EnderecoDAO dao) {
		super(Endereco.class, dao);
	}

}
