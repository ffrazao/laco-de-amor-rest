package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.frazao.lacodeamorrest.dao.laco_de_amor.UsuarioDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.UsuarioFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Usuario;

public class UsuarioDAOFiltroImpl implements UsuarioDAOFiltro {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Usuario> filtrar(final UsuarioFiltroDTO f) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT em.*").append("\n");
		sql.append("FROM   laco_de_amor.usuario as em").append("\n");

		final StringBuilder arg = new StringBuilder();
		if (StringUtils.isNotBlank(f.getLogin())) {
			arg.append(this.adWhere(arg)).append("em.login like :login").append("\n");
		}
		if (StringUtils.isNotBlank(f.getEmail())) {
			arg.append(this.adWhere(arg)).append("em.email like :email").append("\n");
		}
		if (ObjectUtils.isNotEmpty(f.getPerfil())) {
			arg.append(this.adWhere(arg)).append("em.perfil = :perfil").append("\n");
		}
		if (ObjectUtils.isNotEmpty(f.getAtivo())) {
			arg.append(this.adWhere(arg)).append("em.ativo = :ativo").append("\n");
		}

		sql.append(arg);
		sql.append("ORDER BY em.login").append("\n");

		final Query query = this.entityManager.createNativeQuery(sql.toString(), Usuario.class);

		if (StringUtils.isNotBlank(f.getLogin())) {
			query.setParameter("login", this.like(f.getLogin()));
		}
		if (StringUtils.isNotBlank(f.getEmail())) {
			query.setParameter("email", this.like(f.getEmail()));
		}
		if (ObjectUtils.isNotEmpty(f.getPerfil())) {
			query.setParameter("perfil",
					f.getPerfil().stream().sorted().map(v -> v.name()).collect(Collectors.joining(",")));
		}
		if (ObjectUtils.isNotEmpty(f.getAtivo())) {
			query.setParameter("ativo", f.getAtivo().name());
		}

		return query.getResultList();
	}

}
