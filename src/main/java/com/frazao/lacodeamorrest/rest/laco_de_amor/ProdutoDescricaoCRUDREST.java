package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.ProdutoDescricaoBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoDescricaoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoDescricao;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "produto-descricao")
public class ProdutoDescricaoCRUDREST
		extends CRUDREST<ProdutoDescricao, java.lang.Integer, ProdutoDescricaoFiltroDTO, ProdutoDescricaoBO> {

	public ProdutoDescricaoCRUDREST(@Autowired final ProdutoDescricaoBO bo) {
		super(bo);
	}

}
