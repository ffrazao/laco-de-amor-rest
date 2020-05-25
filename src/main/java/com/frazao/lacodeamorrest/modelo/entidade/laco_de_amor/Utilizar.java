package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Utilizar")
@Table(name = "utilizar")
@PrimaryKeyJoinColumn(name = "id")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Utilizar extends Evento {

	private static final long serialVersionUID = 1L;

	public static final String CODIGO = "UTILIZAR";

	@Override
	public String toString() {
		return String.format("%s", this.getId());
	}

}
