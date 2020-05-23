package com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor;

public enum ProdutoPrecoDestinacao {

	COMPRA("Compra"), VENDA("Venda");

	private String descricao;

	private ProdutoPrecoDestinacao(final String descricao) {
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