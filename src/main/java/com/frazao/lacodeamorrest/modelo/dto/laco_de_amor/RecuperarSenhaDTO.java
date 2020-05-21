package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import javax.validation.constraints.NotBlank;

import com.frazao.lacodeamorrest.modelo.dto.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RecuperarSenhaDTO implements DTO {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String email;
	
	public RecuperarSenhaDTO(String email) {
		this.setEmail(email);
	}

}
