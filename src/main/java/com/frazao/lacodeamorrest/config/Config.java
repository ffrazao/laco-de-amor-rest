package com.frazao.lacodeamorrest.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class Config implements WebMvcConfigurer {
	
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor);
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
	    registry.addConverter(new StringToLocalDateConverter());
	    registry.addConverter(new StringToLocalDateTimeConverter());
	    registry.addConverter(new StringToBigDecimalConverter());
	}

	@PostConstruct
	public ObjectMapper configureMapper() {
		this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		this.mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

		this.mapper.configure(MapperFeature.ALLOW_COERCION_OF_SCALARS, true);
		this.mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);

		final SimpleModule module = new SimpleModule();

		module.addDeserializer(LocalDate.class,
				new LocalDateDeserializer(DateTimeFormatter.ofPattern(FormatConfig.DATE_FORMAT[0])));
		module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(FormatConfig.DATE_FORMAT[0])));

		module.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(FormatConfig.DATETIME_FORMAT[0])));
		module.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(FormatConfig.DATETIME_FORMAT[0])));

		module.addDeserializer(BigDecimal.class, new StringToBigDecimalDeserializer());

		this.mapper.registerModule(module);

		return this.mapper;
	}

}
