package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoAtributoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoAtributoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoAtributo;

@Service
public class ProdutoAtributoBO extends CRUDBO<ProdutoAtributo, java.lang.Integer, ProdutoAtributoFiltroDTO> {

	public ProdutoAtributoBO(@Autowired final ProdutoAtributoDAO dao) {
		super(ProdutoAtributo.class, dao);
	}

	@Override
	public ProdutoAtributoDAO getDAO() {
		return (ProdutoAtributoDAO) super.getDAO();
	}

}
