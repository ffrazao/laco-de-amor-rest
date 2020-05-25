package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.CotarDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.CotarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Cotar;

@Service
public class CotarBO extends CRUDBO<Cotar, Integer, CotarFiltroDTO> {
	
	public CotarBO(@Autowired final CotarDAO dao) {
		super(Cotar.class, dao);
	}

	@Autowired
	private EventoBO eventoBO;
	
	@Override
	public Cotar novo(Cotar modelo) {
		Cotar result = (Cotar) this.eventoBO.novo(super.novo(modelo));
		return result;
	}

}
