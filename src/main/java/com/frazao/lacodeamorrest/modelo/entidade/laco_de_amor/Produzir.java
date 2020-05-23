package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Produzir")
@Table(name = "produzir")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Produzir extends Evento {

	private static final long serialVersionUID = 1L;

}
