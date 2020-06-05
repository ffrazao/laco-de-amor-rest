package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import com.frazao.lacodeamorrest.modelo.dto.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImagemVendaDTO implements DTO {

	private static final long serialVersionUID = 1L;

	private String codigo;

	private byte[] foto;

	private Integer id;

	private String nome;

	public ImagemVendaDTO(Integer id, String nome, String codigo, byte[] foto) {
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.foto = foto;
	}

	public String toString() {
		return String.format("%d", this.getId());
	}

}
