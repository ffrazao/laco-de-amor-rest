package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ClienteDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ClienteFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Cliente;

@Service
public class ClienteBO extends CRUDBO<Cliente, Integer, ClienteFiltroDTO, ClienteDAO> {

	public ClienteBO(@Autowired final ClienteDAO dao) {
		super(Cliente.class, dao);
	}

}
