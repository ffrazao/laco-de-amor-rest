package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import java.time.LocalDate;

import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.dto.FiltroIdDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EventoFiltroDTO extends FiltroIdDTO {

	private static final long serialVersionUID = 1L;

	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private String participante;
	private String produto;
	private Confirmacao utilizado;

}