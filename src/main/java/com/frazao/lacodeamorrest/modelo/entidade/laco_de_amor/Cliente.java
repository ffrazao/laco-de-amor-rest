package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Cliente")
@Table(name = "cliente")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Integer id;

	@MapsId
	@JoinColumn(name = "id", insertable = false, updatable = false)
	@OneToOne
	@JsonIgnore
	private Pessoa pessoa;

}
