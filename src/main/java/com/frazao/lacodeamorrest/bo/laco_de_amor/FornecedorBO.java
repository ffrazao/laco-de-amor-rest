package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.FornecedorDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.FornecedorFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Fornecedor;

@Service
public class FornecedorBO extends CRUDBO<Fornecedor, Integer, FornecedorFiltroDTO, FornecedorDAO> {

	public FornecedorBO(@Autowired final FornecedorDAO dao) {
		super(Fornecedor.class, dao);
	}

}
