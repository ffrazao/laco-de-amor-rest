package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoDescricaoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoDescricaoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoDescricao;

@Service
public class ProdutoDescricaoBO
		extends CRUDBO<ProdutoDescricao, Integer, ProdutoDescricaoFiltroDTO, ProdutoDescricaoDAO> {

	public ProdutoDescricaoBO(@Autowired final ProdutoDescricaoDAO dao) {
		super(ProdutoDescricao.class, dao);
	}

}
