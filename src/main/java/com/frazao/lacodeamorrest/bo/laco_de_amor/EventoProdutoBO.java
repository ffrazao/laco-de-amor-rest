package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoProdutoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoProdutoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoProduto;

@Service
public class EventoProdutoBO extends CRUDBO<EventoProduto, java.lang.Integer, EventoProdutoFiltroDTO> {

	public EventoProdutoBO(@Autowired final EventoProdutoDAO dao) {
		super(EventoProduto.class, dao);
	}

	@Override
	public EventoProdutoDAO getDAO() {
		return (EventoProdutoDAO) super.getDAO();
	}

}
