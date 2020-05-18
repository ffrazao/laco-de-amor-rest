package com.frazao.lacodeamorrest.modelo.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrocarSenhaDTO extends AutorizarTrocarSenhaDTO {

	@NotBlank
	private String senha;

}
