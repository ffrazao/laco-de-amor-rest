package com.frazao.lacodeamorrest.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class Ferramenta {

	public static BigDecimal converterStringParaBigDecimal(String str) {
		BigDecimal result = null;
		if (StringUtils.isNotBlank(str)) {
			try {
				int pos[] = { str.lastIndexOf(","), str.lastIndexOf(".") };
				if (pos[0] >= 0 && ((pos[1] == -1) || (pos[1] >= 0 && pos[0] > pos[1]))) {
					str = str.substring(0, pos[0]).concat(".")
							.concat(str.substring(pos[0] + 1, str.length()));
				}
				pos[0] = str.lastIndexOf(".");
				if (pos[0] >= 0) {
					String[] part = { str.substring(0, pos[0]).replaceAll("\\,", "").replaceAll("\\.", ""),
							str.substring(pos[0], str.length()) };
					str = part[0].concat(part[1]);
				}
				str = str.replaceAll("\\,", "");
				result = new BigDecimal(str);
			} catch (Exception e) {
			}
		}

		if (StringUtils.isNotBlank(str) && result == null) {
			throw new NumberFormatException(String.format("Número inválido [%s]", str));
		}
		return result;
	}

}
