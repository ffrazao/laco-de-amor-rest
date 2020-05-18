package com.frazao.lacodeamorrest.modelo.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RecuperarSenhaDTO implements DTO {

	@NotBlank
	private String email;

}
