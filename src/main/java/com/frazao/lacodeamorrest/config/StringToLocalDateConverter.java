package com.frazao.lacodeamorrest.config;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String source) {
		LocalDate result = null;
		if (StringUtils.isNotBlank(source)) {
			try {
				Instant instant = Instant.parse(source);
				result = LocalDate.ofEpochDay(instant.getEpochSecond());
			} catch (DateTimeException e) {
			}
			if (result == null) {
				for (String format : FormatConfig.DATE_FORMAT) {
					try {
						result = LocalDate.parse(source, DateTimeFormatter.ofPattern(format));
						break;
					} catch (DateTimeException e) {
					}
				}
			}
		}
		if (StringUtils.isNotBlank(source) && result == null) {
			throw new DateTimeException(String.format("Data inválida [%s]", source));
		}
		return result;
	}
}
