package com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor;

public enum PessoaVinculoTipo {

	CLIENTE("Cliente"), FORNECEDOR("Fornecedor"), PARCEIRO("Parceiro");

	private String descricao;

	private PessoaVinculoTipo(final String descricao) {
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
