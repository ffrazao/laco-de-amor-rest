package com.frazao.lacodeamorrest.util.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendRecuperarToken(MensagemEmail msg) {
		SimpleMailMessage result = prepareSimpleMailMessageFromRecuperarToken(msg);
		sendMail(result);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromRecuperarToken(MensagemEmail msg) {
		SimpleMailMessage result = new SimpleMailMessage();
		result.setTo(msg.getEmail());
		result.setFrom(sender);
		result.setSubject("Recuperação de senha");
		result.setSentDate(new Date(System.currentTimeMillis()));
		result.setText(msg.toMensagemEmail());

		return result;
	}

}