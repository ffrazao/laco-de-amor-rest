package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoAtributoDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoAtributoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoAtributo;

public class ProdutoAtributoDAOFiltroImpl implements ProdutoAtributoDAOFiltro {

	@Value("${default.database_schema}")
	private String databaseSchema;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProdutoAtributo> filtrar(final ProdutoAtributoFiltroDTO f) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT em.*").append("\n");
		sql.append("FROM   ").append(this.databaseSchema).append(".produto_atributo as em").append("\n");
		final StringBuilder arg = new StringBuilder();
		Integer[] idSim = {};
		Integer[] idNao = {};
		if (ObjectUtils.isNotEmpty(f.getId())) {
			idSim = this.idSim(f.getId());
			if (ObjectUtils.isNotEmpty(idSim)) {
				arg.append(this.adWhere(arg)).append("em.id in :idSim").append("\n");
			}
			idNao = this.idNao(f.getId());
			if (ObjectUtils.isNotEmpty(idNao)) {
				arg.append(this.adWhere(arg)).append("em.id not in :idNao").append("\n");
			}
		}
		if (StringUtils.isNotBlank(f.getNome())) {
			arg.append(this.adWhere(arg)).append("em.nome like :nome").append("\n");
		}
		sql.append(arg);
		sql.append("ORDER BY nome").append("\n");
		final Query query = this.entityManager.createNativeQuery(sql.toString(), ProdutoAtributo.class);
		if (ObjectUtils.isNotEmpty(idSim)) {
			query.setParameter("idSim", new HashSet<>(Arrays.asList(idSim)));
		}
		if (ObjectUtils.isNotEmpty(idNao)) {
			query.setParameter("idNao", new HashSet<>(Arrays.asList(idNao)));
		}
		if (StringUtils.isNotBlank(f.getNome())) {
			query.setParameter("nome", this.like(f.getNome()));
		}
		return query.getResultList();

	}

}
