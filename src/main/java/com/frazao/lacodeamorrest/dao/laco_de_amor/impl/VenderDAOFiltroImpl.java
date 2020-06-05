package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoDAO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.VenderDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.VenderFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Vender;

public class VenderDAOFiltroImpl implements VenderDAOFiltro {

	@Value("${default.database_schema}")
	private String databaseSchema;

	@Autowired
	private EventoDAO eventoDAO;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Vender> filtrar(final VenderFiltroDTO f) {
		final Collection<Vender> result = this.eventoDAO.filtrar("vender", Vender.class, f).getResultList();
		return result;
	}

}
