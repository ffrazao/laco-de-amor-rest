package com.frazao.lacodeamorrest.dao.laco_de_amor;

import javax.persistence.Query;

import com.frazao.lacodeamorrest.dao.Filtro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Evento;

public interface EventoDAOFiltro extends Filtro<Evento, EventoFiltroDTO> {

	Query filtrar(final String tabela, final Class<? extends Evento> clazz, final EventoFiltroDTO f);

}
