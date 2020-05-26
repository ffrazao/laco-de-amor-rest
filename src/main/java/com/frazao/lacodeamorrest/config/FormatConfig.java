package com.frazao.lacodeamorrest.config;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class FormatConfig {

	public static final String[] DATE_FORMAT = { "yyyy-MM-dd", "dd/MM/yyyy", };

	public static final String[] DATETIME_FORMAT = { 
			FormatConfig.DATE_FORMAT[0] + "'T'HH:mm",
			FormatConfig.DATE_FORMAT[0] + "'T'HH:mm:ss",
			FormatConfig.DATE_FORMAT[0] + "'T'HH:mm:ss.SSS",
			FormatConfig.DATE_FORMAT[1] + " HH:mm", 
			FormatConfig.DATE_FORMAT[1] + " HH:mm:ss",
			FormatConfig.DATE_FORMAT[1] + " HH:mm:ss.SSS",
			};

	// este código atua nas variáveis incluidas no body das requisições
	@Bean
	public FormattingConversionService conversionService() {
		final DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

		final DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setDateFormatter(DateTimeFormatter.ofPattern(FormatConfig.DATE_FORMAT[0]));
		registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(FormatConfig.DATETIME_FORMAT[0]));
		registrar.registerFormatters(conversionService);

		// other desired formatters

		return conversionService;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		final LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Bean
	public LocaleResolver localeResolver() {
		final SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("pt", "BR"));
		return slr;
	}

}
