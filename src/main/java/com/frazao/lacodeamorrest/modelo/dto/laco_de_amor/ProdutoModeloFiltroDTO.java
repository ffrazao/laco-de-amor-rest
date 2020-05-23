package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import java.util.Set;

import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode

public class ProdutoModeloFiltroDTO implements FiltroDTO {

	private static final long serialVersionUID = 1L;
	
	private String codigo;

	private String nome;

	private Set<Confirmacao> materiaPrima;

}