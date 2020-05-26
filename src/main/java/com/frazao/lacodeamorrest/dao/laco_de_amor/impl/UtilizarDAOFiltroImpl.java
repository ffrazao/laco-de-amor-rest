package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoDAO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.UtilizarDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.UtilizarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Utilizar;

public class UtilizarDAOFiltroImpl implements UtilizarDAOFiltro {

	@Value("${default.database_schema}")
	private String databaseSchema;

	@Autowired
	private EventoDAO eventoDAO;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Utilizar> filtrar(final UtilizarFiltroDTO f) {
		Collection<Utilizar> result = (Collection<Utilizar>) this.eventoDAO.filtrar("utilizar", Utilizar.class, f)
				.getResultList();
		return result;
	}

}
