package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Usuario")
@Table(name = "usuario")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
public class Usuario extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "ativo")
	private Confirmacao ativo;

	@Column(name = "email")
	private String email;

	@Lob
	@Column(name = "foto")
	private byte[] foto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "login")
	private String login;

	@Column(name = "perfil")
	private String perfil;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@JsonIgnore
	@Column(name = "recuperar_senha_expira")
	private Long recuperarSenhaExpira;

	@JsonIgnore
	@Column(name = "recuperar_senha_token")
	private String recuperarSenhaToken;

	@JsonIgnore
	@Column(name = "senha")
	private String senha;

	public Usuario(final Integer id) {
		super(id);
	}

	public Optional<Pessoa> getPessoa() {
		return Optional.ofNullable(this.pessoa);
	}

	@Override
	public String toString() {
		return String.format("Id = %d", this.getId());
	}

}
