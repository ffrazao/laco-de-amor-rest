package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoPrecoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoPrecoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoPreco;

@Service
public class ProdutoPrecoBO extends CRUDBO<ProdutoPreco, java.lang.Integer, ProdutoPrecoFiltroDTO> {

	public ProdutoPrecoBO(@Autowired final ProdutoPrecoDAO dao) {
		super(ProdutoPreco.class, dao);
	}

	@Override
	public ProdutoPrecoDAO getDAO() {
		return (ProdutoPrecoDAO) super.getDAO();
	}

}
