package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoDAO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProduzirDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProduzirFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Produzir;

public class ProduzirDAOFiltroImpl implements ProduzirDAOFiltro {

	@Value("${default.database_schema}")
	private String databaseSchema;

	@Autowired
	private EventoDAO eventoDAO;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Produzir> filtrar(final ProduzirFiltroDTO f) {
		Collection<Produzir> result = (Collection<Produzir>) this.eventoDAO.filtrar("produzir", Produzir.class, f)
				.getResultList();
		return result;
	}

}
