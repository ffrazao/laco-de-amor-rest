package com.frazao.lacodeamorrest.util.email;

import java.util.Map;

public interface MensagemEmail {
	
	default String toMensagemEmail() {
		return this.toMensagemEmail(null);
	}
	
	String toMensagemEmail(Map<String, Object> parametro);
	
	String getEmail();

}
