package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoPessoaFuncaoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoPessoaFuncaoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoPessoaFuncao;

@Service
public class EventoPessoaFuncaoBO extends CRUDBO<EventoPessoaFuncao, java.lang.Integer, EventoPessoaFuncaoFiltroDTO> {

	public EventoPessoaFuncaoBO(@Autowired final EventoPessoaFuncaoDAO dao) {
		super(EventoPessoaFuncao.class, dao);
	}

	@Override
	public EventoPessoaFuncaoDAO getDAO() {
		return (EventoPessoaFuncaoDAO) super.getDAO();
	}

}
