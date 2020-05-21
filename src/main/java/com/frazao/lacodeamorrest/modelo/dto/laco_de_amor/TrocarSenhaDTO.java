package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class TrocarSenhaDTO extends AutorizarTrocarSenhaDTO {
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String senha;

	public TrocarSenhaDTO(AutorizarTrocarSenhaDTO autorizarTrocarSenhaDTO, String senha) {
		super(autorizarTrocarSenhaDTO, autorizarTrocarSenhaDTO.getToken());
		this.setSenha(senha);
	}

}
