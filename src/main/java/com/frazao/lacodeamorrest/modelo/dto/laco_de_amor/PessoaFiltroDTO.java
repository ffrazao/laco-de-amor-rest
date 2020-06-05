package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import java.util.HashSet;
import java.util.Set;

import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.PessoaTipo;
import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.PessoaVinculoTipo;
import com.frazao.lacodeamorrest.modelo.dto.FiltroIdDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PessoaFiltroDTO extends FiltroIdDTO {

	private static final long serialVersionUID = 1L;

	private String cpfCnpj;

	private String nome;

	private Set<PessoaTipo> pessoaTipo = new HashSet<>();

	private Set<PessoaVinculoTipo> pessoaVinculoTipo = new HashSet<>();

}
