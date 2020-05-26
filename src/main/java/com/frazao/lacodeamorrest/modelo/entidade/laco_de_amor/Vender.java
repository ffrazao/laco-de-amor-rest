package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Vender")
@Table(name = "vender")
@PrimaryKeyJoinColumn(name = "id")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Vender extends Evento {

	private static final long serialVersionUID = 1L;

	public static final String CODIGO = "VENDER";

	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@Override
	public String toString() {
		return String.format("%s", this.getId());
	}

}
