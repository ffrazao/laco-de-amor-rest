package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import java.util.Set;

import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.dto.FiltroIdDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProdutoModeloFiltroDTO extends FiltroIdDTO {

	private static final long serialVersionUID = 1L;

	private String codigo;

	private Set<Confirmacao> materiaPrima;

	private String nome;

}