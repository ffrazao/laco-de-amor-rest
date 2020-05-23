package com.frazao.lacodeamorrest.dao.laco_de_amor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Comprar;

@Repository
public interface ComprarDAO extends JpaRepository<Comprar, Integer>, ComprarDAOFiltro {

}
