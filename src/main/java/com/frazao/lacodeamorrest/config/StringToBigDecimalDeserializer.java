package com.frazao.lacodeamorrest.config;

import static com.frazao.lacodeamorrest.util.Ferramenta.converterStringParaBigDecimal;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

// necessário para desserializar conteudo de json
public class StringToBigDecimalDeserializer extends StdDeserializer<BigDecimal> {

	private static final long serialVersionUID = 1L;

	public StringToBigDecimalDeserializer() {
		this(null);
	}

	public StringToBigDecimalDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public BigDecimal deserialize(JsonParser parser, DeserializationContext deserializer) {
		BigDecimal result;
		try {
			result = converterStringParaBigDecimal(parser.getText());
		} catch (IOException e) {
			throw new IllegalArgumentException(String.format("Número inválido"));
		}

		return result;
	}
}
