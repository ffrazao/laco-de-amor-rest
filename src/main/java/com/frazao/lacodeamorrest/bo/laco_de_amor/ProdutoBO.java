package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Produto;

@Service
public class ProdutoBO extends CRUDBO<Produto, Integer, ProdutoFiltroDTO, ProdutoDAO> {

	public ProdutoBO(@Autowired final ProdutoDAO dao) {
		super(Produto.class, dao);
	}

}
