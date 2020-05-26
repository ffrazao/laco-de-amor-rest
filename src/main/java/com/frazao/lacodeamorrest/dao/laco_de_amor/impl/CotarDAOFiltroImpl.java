package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.frazao.lacodeamorrest.dao.laco_de_amor.CotarDAOFiltro;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.CotarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Cotar;

public class CotarDAOFiltroImpl implements CotarDAOFiltro {

	@Value("${default.database_schema}")
	private String databaseSchema;
	
	@Autowired
	private EventoDAO eventoDAO;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Cotar> filtrar(final CotarFiltroDTO f) {
		Collection<Cotar> result = (Collection<Cotar>) this.eventoDAO.filtrar("cotar", Cotar.class, f).getResultList();
		return result;
	}

}
