package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoModeloDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoModeloFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoModelo;

@Service
public class ProdutoModeloBO extends CRUDBO<ProdutoModelo, java.lang.Integer, ProdutoModeloFiltroDTO> {

	public ProdutoModeloBO(@Autowired final ProdutoModeloDAO dao) {
		super(ProdutoModelo.class, dao);
	}

	@Override
	public ProdutoModeloDAO getDAO() {
		return (ProdutoModeloDAO) super.getDAO();
	}

}
