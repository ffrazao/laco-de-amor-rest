package com.frazao.lacodeamorrest.modelo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UsuarioFiltroDTO implements FiltroDTO {

	private static final long serialVersionUID = 1L;

	private String nome;

}
