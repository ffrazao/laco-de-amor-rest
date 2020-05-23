package com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor;

public enum PessoaTipo {

	PF("Pessoa Física"), PJ("Pessoa Jurídica");

	private String descricao;

	private PessoaTipo(final String descricao) {
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
