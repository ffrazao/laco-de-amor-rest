package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Endereco")
@Table(name = "endereco")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Endereco extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cep")
	@Pattern(regexp = "[0-9]{5}-[0-9]{3}")
	private String cep;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "complemento")
	private String complemento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id")
	private Integer id;

	@Column(name = "logradouro")
	@NotBlank
	private String logradouro;

	@Column(name = "numero")
	private String numero;

	@Column(name = "uf")
	private String uf;

	public Endereco(final Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return String.format("Id = %d", this.getId());
	}

}
