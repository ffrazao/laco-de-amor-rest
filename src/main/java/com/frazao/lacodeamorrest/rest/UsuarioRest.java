package com.frazao.lacodeamorrest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.UsuarioBO;
import com.frazao.lacodeamorrest.modelo.entidade.Usuario;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioRest extends CrudRest<Usuario, Integer, UsuarioBO> {

	public UsuarioRest(@Autowired UsuarioBO bo) {
		super(bo);
	}

	@GetMapping("login/{login}")
	public Usuario findByLogin(@PathVariable("login") String valor) {
		return this.getBO().findByLogin(valor);
	}

}
