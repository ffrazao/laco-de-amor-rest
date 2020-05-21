package com.frazao.lacodeamorrest.modelo.dto.laco_de_amor;

import java.util.Set;

import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.Perfil;
import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UsuarioFiltroDTO implements FiltroDTO {

	private static final long serialVersionUID = 1L;

	private String login;
	
	private String email;
	
	private Set<Perfil> perfil;
	
	private Confirmacao ativo;

}
