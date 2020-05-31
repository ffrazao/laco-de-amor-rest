package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoPessoaDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoPessoaFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoPessoa;

@Service
public class EventoPessoaBO extends CRUDBO<EventoPessoa, Integer, EventoPessoaFiltroDTO, EventoPessoaDAO> {

	@Autowired
	private EventoProdutoBO eventoProdutoBO;

	public EventoPessoaBO(@Autowired final EventoPessoaDAO dao) {
		super(EventoPessoa.class, dao);
	}

	@Override
	public void excluindo(final EventoPessoa anterior, final Integer id) throws BOException {
		if (anterior.getEventoProdutoList() != null) {
			anterior.getEventoProdutoList().forEach(e -> e.setEventoPessoa(null));
			this.eventoProdutoBO.delete(this.idList(anterior.getEventoProdutoList()));
		}
	}

}
