package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import java.util.Set;

import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.UsuarioPerfil;
import com.frazao.lacodeamorrest.modelo.dto.FiltroIdDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsuarioFiltroDTO extends FiltroIdDTO {

	private static final long serialVersionUID = 1L;

	private Confirmacao ativo;

	private String email;

	private String login;

	private Set<UsuarioPerfil> perfil;

}
