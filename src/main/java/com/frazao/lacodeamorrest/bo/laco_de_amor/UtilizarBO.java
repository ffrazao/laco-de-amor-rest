package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.UtilizarDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.UtilizarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Utilizar;

@Service
public class UtilizarBO extends CRUDBO<Utilizar, Integer, UtilizarFiltroDTO> {

	public UtilizarBO(@Autowired final UtilizarDAO dao) {
		super(Utilizar.class, dao);
	}

	@Autowired
	private EventoBO eventoBO;

	@Override
	public Utilizar novo(Utilizar modelo) {
		Utilizar result = (Utilizar) this.eventoBO.novo(super.novo(modelo));
		return result;
	}

}
