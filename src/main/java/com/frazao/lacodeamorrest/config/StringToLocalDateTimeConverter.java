package com.frazao.lacodeamorrest.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(String source) {
		LocalDateTime result = LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DateTimeConfig.DATETIME_FORMAT));
		return result;
	}
}
