package com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor;

public enum Perfil {

	Admin("Administrador"), Cliente("Cliente"), Parceiro("Parceiro");
	
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
