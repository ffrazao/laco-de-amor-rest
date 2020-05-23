package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.ProdutoAtributoBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoAtributoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoAtributo;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "produto-atributo")
public class ProdutoAtributoCRUDREST
		extends CRUDREST<ProdutoAtributo, java.lang.Integer, ProdutoAtributoFiltroDTO, ProdutoAtributoBO> {

	public ProdutoAtributoCRUDREST(@Autowired final ProdutoAtributoBO bo) {
		super(bo);
	}

}
