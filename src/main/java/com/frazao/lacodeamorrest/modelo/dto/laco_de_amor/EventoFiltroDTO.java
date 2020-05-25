package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import java.time.LocalDate;

import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class EventoFiltroDTO implements FiltroDTO {

	private static final long serialVersionUID = 1L;

	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private String produto;
	private String participante;

}