package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import java.util.Set;

import com.frazao.lacodeamorrest.modelo.dominio.PessoaTipo;
import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PessoaFiltroDTO implements FiltroDTO {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String cpfCnpj;
	
	private Set<PessoaTipo> tipo;

}
