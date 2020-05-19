package com.frazao.lacodeamorrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frazao.lacodeamorrest.modelo.entidade.Pessoa;

public interface PessoaDAO extends JpaRepository<Pessoa, Integer> {

}
