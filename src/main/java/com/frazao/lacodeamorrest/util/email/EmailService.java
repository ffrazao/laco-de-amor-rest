package com.frazao.lacodeamorrest.util.email;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

	void sendRecuperarToken(MensagemEmail msg);

	void sendMail(SimpleMailMessage msg); 

}