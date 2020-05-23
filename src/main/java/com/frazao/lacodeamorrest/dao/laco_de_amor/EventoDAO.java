package com.frazao.lacodeamorrest.dao.laco_de_amor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Evento;

@Repository
public interface EventoDAO extends JpaRepository<Evento, java.lang.Integer>, EventoDAOFiltro {

}
