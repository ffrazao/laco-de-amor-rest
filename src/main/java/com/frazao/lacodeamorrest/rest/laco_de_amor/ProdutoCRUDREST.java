package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.ProdutoBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Produto;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "produto")
public class ProdutoCRUDREST extends CRUDREST<Produto, java.lang.Integer, ProdutoFiltroDTO, ProdutoBO> {

	public ProdutoCRUDREST(@Autowired final ProdutoBO bo) {
		super(bo);
	}

}
