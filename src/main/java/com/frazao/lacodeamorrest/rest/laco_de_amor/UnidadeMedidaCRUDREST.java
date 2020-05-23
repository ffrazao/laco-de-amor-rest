package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.UnidadeMedidaBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.UnidadeMedidaFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.UnidadeMedida;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "unidade-medida")
public class UnidadeMedidaCRUDREST
		extends CRUDREST<UnidadeMedida, java.lang.Integer, UnidadeMedidaFiltroDTO, UnidadeMedidaBO> {

	public UnidadeMedidaCRUDREST(@Autowired final UnidadeMedidaBO bo) {
		super(bo);
	}

}
