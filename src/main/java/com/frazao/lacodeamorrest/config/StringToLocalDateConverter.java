package com.frazao.lacodeamorrest.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String source) {
		LocalDate result = LocalDate.parse(source, DateTimeFormatter.ofPattern(DateTimeConfig.DATE_FORMAT));
		return result;
	}
}
