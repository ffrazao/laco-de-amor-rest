package com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor;

public enum ProdutoPrecoDestinacao {

	Compra("Compra"), Venda("Venda");

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