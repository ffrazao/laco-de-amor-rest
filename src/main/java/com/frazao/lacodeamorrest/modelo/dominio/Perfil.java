package com.frazao.lacodeamorrest.modelo.dominio;

public enum Perfil {

	Admin("Administrador"), Parceiro("Parceiro"), Cliente("Cliente");
	
	private String descricao;
	
	private Perfil(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	@Override
	public String toString() {
		return this.getDescricao();
	}
	
}
