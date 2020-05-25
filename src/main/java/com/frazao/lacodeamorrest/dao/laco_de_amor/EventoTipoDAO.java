package com.frazao.lacodeamorrest.dao.laco_de_amor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoTipo;

@Repository
public interface EventoTipoDAO extends JpaRepository<EventoTipo, java.lang.Integer>, EventoTipoDAOFiltro {

	EventoTipo getFindByCodigo(String codigo);

}
