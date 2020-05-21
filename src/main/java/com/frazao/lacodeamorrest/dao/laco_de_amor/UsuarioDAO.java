package com.frazao.lacodeamorrest.dao.laco_de_amor;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer>, UsuarioDAOFiltro {

	Usuario findByLogin(String valor);

	Usuario findByEmail(String email);

	Usuario findByEmailAndRecuperarSenhaToken(String email, String token);

}
