package com.frazao.lacodeamorrest.modelo.dominio;

public enum Confirmacao {

	N("Não"), S("Sim");

	private String descricao;

	private Confirmacao(final String descricao) {
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
