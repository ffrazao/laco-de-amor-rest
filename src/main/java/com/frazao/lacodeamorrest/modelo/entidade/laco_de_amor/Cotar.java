package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Cotar")
@Table(name = "cotar")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Cotar extends Evento {

	private static final long serialVersionUID = 1L;

}