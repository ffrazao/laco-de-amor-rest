package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Cotar")
@Table(name = "cotar")
@PrimaryKeyJoinColumn(name = "id")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cotar extends Evento {

	public static final String CODIGO = "COTAR";

	private static final long serialVersionUID = 1L;

	public Cotar(final Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return String.format("%s", this.getId());
	}

}