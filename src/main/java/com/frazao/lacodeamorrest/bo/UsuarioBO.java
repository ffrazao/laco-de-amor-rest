package com.frazao.lacodeamorrest.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.dao.UsuarioDAO;
import com.frazao.lacodeamorrest.modelo.entidade.Usuario;

@Service
public class UsuarioBO extends CRUDBO<Usuario, Integer> {


	public UsuarioBO(@Autowired UsuarioDAO dao) {
		super(dao);
	}
	
	public UsuarioDAO getDAO() {
		return (UsuarioDAO) super.getDAO();
	}
	
	public Usuario findByLogin(String valor) {
		return this.getDAO().findByLogin(valor);
	}
	
	
}
