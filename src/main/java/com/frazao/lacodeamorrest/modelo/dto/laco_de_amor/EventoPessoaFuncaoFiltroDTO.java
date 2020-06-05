package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import com.frazao.lacodeamorrest.modelo.dto.FiltroIdDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EventoPessoaFuncaoFiltroDTO extends FiltroIdDTO {

	private static final long serialVersionUID = 1L;

	public String codigo;

	public String nome;

}