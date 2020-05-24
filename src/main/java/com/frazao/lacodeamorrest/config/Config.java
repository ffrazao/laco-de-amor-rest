package com.frazao.lacodeamorrest.config;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class Config {

	public static final String DATE_FORMAT = "dd/MM/yyyy";

	public static final String DATETIME_FORMAT = Config.DATE_FORMAT + " HH:mm:ss";

	@Autowired
	ObjectMapper mapper;

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
				new LocalDateDeserializer(DateTimeFormatter.ofPattern(Config.DATE_FORMAT)));
		module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(Config.DATE_FORMAT)));
		module.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(Config.DATETIME_FORMAT)));
		module.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(Config.DATETIME_FORMAT)));
		module.addSerializer(BigInteger.class, new ToStringSerializer());

		this.mapper.registerModule(module);

		return this.mapper;
	}
}