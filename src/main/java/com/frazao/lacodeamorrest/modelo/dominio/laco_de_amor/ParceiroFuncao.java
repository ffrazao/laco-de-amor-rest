package com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor;

public enum ParceiroFuncao {

	Entregador("Entregador(a)"), Costureiro("Costureiro(a)");
	
	private String descricao;
	
	private ParceiroFuncao(String descricao) {
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
