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

import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoPessoaFuncaoDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoPessoaFuncaoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoPessoaFuncao;

public class EventoPessoaFuncaoDAOFiltroImpl implements EventoPessoaFuncaoDAOFiltro {

	@Value("${default.database_schema}")
	private String databaseSchema;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<EventoPessoaFuncao> filtrar(final EventoPessoaFuncaoFiltroDTO f) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT em.*").append("\n");
		sql.append("FROM   ").append(this.databaseSchema).append(".evento_pessoa_funcao as em").append("\n");
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
		final StringBuilder arg1 = new StringBuilder();
		if (StringUtils.isNotBlank(f.getNome())) {
			arg1.append(this.adOr(arg1)).append("(em.nome like :nome)").append("\n");
		}
		if (StringUtils.isNotBlank(f.getCodigo())) {
			arg1.append(this.adOr(arg1)).append("(em.codigo like :codigo)").append("\n");
		}
		if (arg1.length() > 0) {
			arg.append(this.adWhere(arg)).append("(").append(arg1).append(")").append("\n");
		}
		sql.append(arg);
		sql.append("ORDER BY 1").append("\n");
		final Query query = this.entityManager.createNativeQuery(sql.toString(), EventoPessoaFuncao.class);
		if (ObjectUtils.isNotEmpty(idSim)) {
			query.setParameter("idSim", new HashSet<>(Arrays.asList(idSim)));
		}
		if (ObjectUtils.isNotEmpty(idNao)) {
			query.setParameter("idNao", new HashSet<>(Arrays.asList(idNao)));
		}
		if (StringUtils.isNotBlank(f.getNome())) {
			query.setParameter("nome", this.like(f.getNome()));
		}
		if (StringUtils.isNotBlank(f.getCodigo())) {
			query.setParameter("codigo", this.like(f.getCodigo()));
		}
		return query.getResultList();

	}

}
