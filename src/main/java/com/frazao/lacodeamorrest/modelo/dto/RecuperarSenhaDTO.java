package com.frazao.lacodeamorrest.modelo.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RecuperarSenhaDTO implements DTO {

	@NotBlank
	private String email;
	
	public RecuperarSenhaDTO(String email) {
		this.setEmail(email);
	}

}
