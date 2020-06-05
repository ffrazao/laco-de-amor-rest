package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.frazao.lacodeamorrest.dao.laco_de_amor.ComprarDAOFiltro;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ComprarFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Comprar;

public class ComprarDAOFiltroImpl implements ComprarDAOFiltro {

	@Value("${default.database_schema}")
	private String databaseSchema;

	@Autowired
	private EventoDAO eventoDAO;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Comprar> filtrar(final ComprarFiltroDTO f) {
		final Collection<Comprar> result = this.eventoDAO.filtrar("comprar", Comprar.class, f).getResultList();
		return result;
	}

}
