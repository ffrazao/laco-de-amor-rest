package com.frazao.lacodeamorrest.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.UsuarioBO;
import com.frazao.lacodeamorrest.modelo.dto.AutorizarTrocarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.RecuperarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.TrocarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.UsuarioFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.Usuario;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioCRUDREST extends CRUDREST<Usuario, Integer, UsuarioFiltroDTO, UsuarioBO> {

	public UsuarioCRUDREST(@Autowired UsuarioBO bo) {
		super(bo);
	}

	@PostMapping("recuperar-senha")
	public void recuperarSenha(@Valid @RequestBody RecuperarSenhaDTO valor) throws Exception {
		this.getBO().recuperarSenha(valor);
	}

	@PostMapping("autorizar-trocar-senha")
	public void autorizarTrocarSenha(@Valid @RequestBody AutorizarTrocarSenhaDTO valor) throws Exception {
		this.getBO().autorizarTrocarSenha(valor);
	}

	@PostMapping("trocar-senha")
	public void trocarSenha(@Valid @RequestBody TrocarSenhaDTO valor) throws Exception {
		this.getBO().trocarSenha(valor);
	}

}
