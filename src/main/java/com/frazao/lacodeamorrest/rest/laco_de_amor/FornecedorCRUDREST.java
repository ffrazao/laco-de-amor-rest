package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.FornecedorBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.FornecedorFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Fornecedor;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "fornecedor")
public class FornecedorCRUDREST extends CRUDREST<Fornecedor, Integer, FornecedorFiltroDTO, FornecedorBO> {

	public FornecedorCRUDREST(@Autowired final FornecedorBO bo) {
		super(bo);
	}

}
