package com.frazao.lacodeamorrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frazao.lacodeamorrest.modelo.entidade.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

	Usuario findByLogin(String valor);

}
