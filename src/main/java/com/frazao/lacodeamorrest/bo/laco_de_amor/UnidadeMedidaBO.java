package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.UnidadeMedidaDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.UnidadeMedidaFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.UnidadeMedida;

@Service
public class UnidadeMedidaBO extends CRUDBO<UnidadeMedida, java.lang.Integer, UnidadeMedidaFiltroDTO> {

	public UnidadeMedidaBO(@Autowired final UnidadeMedidaDAO dao) {
		super(UnidadeMedida.class, dao);
	}

	@Override
	public UnidadeMedidaDAO getDAO() {
		return (UnidadeMedidaDAO) super.getDAO();
	}

}
