package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoProdutoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoProdutoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoProduto;

@Service
public class EventoProdutoBO
		extends CRUDBO<EventoProduto, java.lang.Integer, EventoProdutoFiltroDTO, EventoProdutoDAO> {

	@Autowired
	private EventoPessoaBO eventoPessoaBO;

	public EventoProdutoBO(@Autowired final EventoProdutoDAO dao) {
		super(EventoProduto.class, dao);
	}

	@Override
	public void excluindo(final EventoProduto anterior, final Integer id) throws BOException {
		if (anterior.getEventoPessoa() != null && anterior.getEventoPessoa().getId() != null) {
			Integer i = anterior.getEventoPessoa().getId();
			anterior.setEventoPessoa(null);
			this.eventoPessoaBO.delete(i);
		}
	}

}
