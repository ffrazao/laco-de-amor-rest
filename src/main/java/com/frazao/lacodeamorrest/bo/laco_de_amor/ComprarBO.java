package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ComprarDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ComprarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Comprar;

@Service
public class ComprarBO extends CRUDBO<Comprar, Integer, ComprarFiltroDTO> {

	public ComprarBO(@Autowired final ComprarDAO dao) {
		super(Comprar.class, dao);
	}

	@Autowired
	private EventoBO eventoBO;

	@Override
	public Comprar novo(Comprar modelo) {
		Comprar result = (Comprar) this.eventoBO.novo(super.novo(modelo));
		return result;
	}

}
