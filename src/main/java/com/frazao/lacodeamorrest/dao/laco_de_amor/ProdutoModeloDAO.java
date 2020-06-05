package com.frazao.lacodeamorrest.dao.laco_de_amor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoModelo;

@Repository
public interface ProdutoModeloDAO extends JpaRepository<ProdutoModelo, Integer>, ProdutoModeloDAOFiltro {

	List<ProdutoModelo> findAllByMateriaPrimaAndFotoIsNotNull(Confirmacao confirmacao);

}
