package com.frazao.lacodeamorrest.modelo.dominio;

public enum PessoaTipo {

	PF("Pessoa Física"), PJ("Pessoa Jurídica");
	
	private String descricao;
	
	private PessoaTipo(String descricao) {
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
