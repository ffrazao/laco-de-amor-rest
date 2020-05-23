package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoAtributoDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoAtributoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoAtributo;

public class ProdutoAtributoDAOFiltroImpl implements ProdutoAtributoDAOFiltro {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProdutoAtributo> filtrar(final ProdutoAtributoFiltroDTO f) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT em.*").append("\n");
		sql.append("FROM   laco_de_amor.produto_atributo as em").append("\n");
		final StringBuilder arg = new StringBuilder();
		if (StringUtils.isNotBlank(f.getNome())) {
			arg.append(adWhere(arg)).append("em.nome like :nome").append("\n");
		}
		sql.append(arg);
		sql.append("ORDER BY nome").append("\n");
		final Query query = this.entityManager.createNativeQuery(sql.toString(), ProdutoAtributo.class);
		if (StringUtils.isNotBlank(f.getNome())) {
			query.setParameter("nome", like(f.getNome()));
		}
		return query.getResultList();

	}

}
