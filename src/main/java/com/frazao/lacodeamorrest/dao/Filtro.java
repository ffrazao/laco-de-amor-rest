package com.frazao.lacodeamorrest.dao;

import java.util.Collection;
import java.util.stream.Collectors;

import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;

public interface Filtro<T, F extends FiltroDTO> {

	default String adWhere(final StringBuilder where) {
		return where.length() == 0 ? "WHERE  " : "AND    ";
	}

	Collection<T> filtrar(F f);

	default String in(final Collection<?> itens) {
		return itens != null ? String.format("(%s)", itens.stream().map(i -> " ?").collect(Collectors.joining(",")).trim()) : null;
	}

	default String like(final String arg) {
		return arg != null ? String.format("%%%s%%", arg.replaceAll(" ", "%")) : null;
	}
}
