package com.frazao.lacodeamorrest.modelo.dto;

import java.util.Arrays;

import lombok.Data;

@Data
public class FiltroIdDTO implements FiltroDTO {

	private static final long serialVersionUID = 1L;
	
	private String[] id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FiltroIdDTO other = (FiltroIdDTO) obj;
		if (!Arrays.equals(id, other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(id);
		return result;
	}

}
