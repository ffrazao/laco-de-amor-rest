package com.frazao.lacodeamorrest.util.email;

import java.util.Map;

public interface MensagemEmail {

	String getEmail();

	default String toMensagemEmail() {
		return this.toMensagemEmail(null);
	}

	String toMensagemEmail(Map<String, Object> parametro);

}
