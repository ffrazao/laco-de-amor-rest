package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import java.util.Map;

import javax.validation.constraints.NotBlank;

import com.frazao.lacodeamorrest.util.email.MensagemEmail;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class AutorizarTrocarSenhaDTO extends RecuperarSenhaDTO implements MensagemEmail {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String token;

	public AutorizarTrocarSenhaDTO(final RecuperarSenhaDTO recuperarSenhaDTO, final String token) {
		super(recuperarSenhaDTO.getEmail());
		this.setToken(token);
	}

	@Override
	public String toMensagemEmail(final Map<String, Object> parametro) {
		final StringBuilder msg = new StringBuilder();
		msg.append(String.format("Utilize o seguinte token para trocar a sua senha")).append("\n");
		msg.append(String.format("Token: %s", this.getToken())).append("\n");
		return msg.toString();
	}

}
