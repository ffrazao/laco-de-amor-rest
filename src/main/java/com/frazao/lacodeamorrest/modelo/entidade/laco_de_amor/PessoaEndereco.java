package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "PessoaEndereco")
@Table(name = "pessoa_endereco")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class PessoaEndereco extends EntidadeBaseTemId<Integer> {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

}
