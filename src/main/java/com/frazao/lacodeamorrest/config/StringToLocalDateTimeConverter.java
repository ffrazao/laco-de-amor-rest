package com.frazao.lacodeamorrest.config;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(String source) {
		LocalDateTime result = null;
		if (StringUtils.isNotBlank(source)) {
			try {
				Instant instant = Instant.parse(source);
				result = LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(),
						ZoneOffset.from(instant.atZone(ZoneId.systemDefault())));
			} catch (DateTimeException e) {
			}
			if (result == null) {
				for (String format : FormatConfig.DATETIME_FORMAT) {
					try {
						result = LocalDateTime.parse(source, DateTimeFormatter.ofPattern(format));
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
