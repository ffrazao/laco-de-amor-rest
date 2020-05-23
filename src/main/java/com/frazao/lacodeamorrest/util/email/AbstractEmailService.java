package com.frazao.lacodeamorrest.util.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	protected SimpleMailMessage prepareSimpleMailMessageFromRecuperarToken(final MensagemEmail msg) {
		final SimpleMailMessage result = new SimpleMailMessage();
		result.setTo(msg.getEmail());
		result.setFrom(this.sender);
		result.setSubject("Recuperação de senha");
		result.setSentDate(new Date(System.currentTimeMillis()));
		result.setText(msg.toMensagemEmail());

		return result;
	}

	@Override
	public void sendRecuperarToken(final MensagemEmail msg) {
		final SimpleMailMessage result = this.prepareSimpleMailMessageFromRecuperarToken(msg);
		this.sendMail(result);
	}

}