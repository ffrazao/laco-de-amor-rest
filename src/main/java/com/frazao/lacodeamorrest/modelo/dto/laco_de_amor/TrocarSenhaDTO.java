package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class TrocarSenhaDTO extends AutorizarTrocarSenhaDTO {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String senha;

	public TrocarSenhaDTO(final AutorizarTrocarSenhaDTO autorizarTrocarSenhaDTO, final String senha) {
		super(autorizarTrocarSenhaDTO, autorizarTrocarSenhaDTO.getToken(), null);
		this.setSenha(senha);
	}

}
