package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.ProdutoPrecoBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoPrecoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoPreco;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "produto-preco")
public class ProdutoPrecoCRUDREST
		extends CRUDREST<ProdutoPreco, java.lang.Integer, ProdutoPrecoFiltroDTO, ProdutoPrecoBO> {

	public ProdutoPrecoCRUDREST(@Autowired final ProdutoPrecoBO bo) {
		super(bo);
	}

}
