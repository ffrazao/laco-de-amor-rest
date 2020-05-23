package com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor;

public enum ParceiroFuncao {

	Costureiro("Costureiro(a)"), Entregador("Entregador(a)");

	private String descricao;

	private ParceiroFuncao(final String descricao) {
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
