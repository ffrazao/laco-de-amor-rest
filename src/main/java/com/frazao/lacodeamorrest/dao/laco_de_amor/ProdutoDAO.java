package com.frazao.lacodeamorrest.dao.laco_de_amor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Produto;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, java.lang.Integer>, ProdutoDAOFiltro {

}
