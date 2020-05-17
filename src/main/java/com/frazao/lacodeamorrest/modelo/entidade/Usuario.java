package com.frazao.lacodeamorrest.modelo.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.EntidadeBaseTemId;
import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.dominio.Perfil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Usuario")
@Table(name = "usuario")
@Data
@EqualsAndHashCode(callSuper = true)
public class Usuario extends EntidadeBaseTemId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "login")
	private String login;
	
	@JsonIgnore
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	
//	private Pessoa pessoa;
	
	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

}
