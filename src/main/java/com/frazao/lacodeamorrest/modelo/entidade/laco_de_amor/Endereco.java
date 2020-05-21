package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Endereco")
@Table(name = "endereco")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Endereco extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@JoinColumn(name = "id")
	private Integer id;

	@Column(name = "logradouro")
	private String logradouro;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "uf")
	private String uf;
	
	@Column(name = "cep")
	private String cep;
	
}
