package com.frazao.lacodeamorrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.frazao.lacodeamorrest.util.email.EmailService;
import com.frazao.lacodeamorrest.util.email.MockEmailService;

@Configuration
@Profile("!heroku.prod")
public class NoProdConfig {

	@Bean
	public EmailService instantiateEmailService() {
		return new MockEmailService();
	}

}
