package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.ParceiroFuncao;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Parceiro")
@Table(name = "parceiro")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Parceiro extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "funcao")
	private ParceiroFuncao funcao;

	@Id
	@Column(name = "id")
	private Integer id;

	@MapsId
	@JoinColumn(name = "id", insertable = false, updatable = false)
	@OneToOne
	@JsonIgnore
	private Pessoa pessoa;

	public Parceiro(final Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return String.format("Id = %d", this.getId());
	}

}
