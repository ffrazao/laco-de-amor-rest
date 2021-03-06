package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Comprar")
@Table(name = "comprar")
@PrimaryKeyJoinColumn(name = "id")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comprar extends Evento {

	public static final String CODIGO = "COMPRAR";

	private static final long serialVersionUID = 1L;

	public Comprar(final Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return String.format("%s", this.getId());
	}

}
