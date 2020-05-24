package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.frazao.lacodeamorrest.dao.laco_de_amor.PessoaDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.PessoaFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Pessoa;

public class PessoaDAOFiltroImpl implements PessoaDAOFiltro {

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${default.database_schema}")
	private String databaseSchema;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Pessoa> filtrar(final PessoaFiltroDTO f) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT em.*").append("\n");
		sql.append("FROM   ").append(databaseSchema).append(".pessoa as em").append("\n");

		final StringBuilder arg = new StringBuilder();
		if (StringUtils.isNotBlank(f.getNome())) {
			arg.append(this.adWhere(arg)).append("em.nome like :nome").append("\n");
		}
		if (StringUtils.isNotBlank(f.getCpfCnpj())) {
			arg.append(this.adWhere(arg)).append("em.cpf_cnpj = :cpfCnpj").append("\n");
		}
		if (ObjectUtils.isNotEmpty(f.getPessoaTipo())) {
			arg.append(this.adWhere(arg)).append("em.tipo in :tipo").append("\n");
		}
		sql.append(arg);
		sql.append("ORDER BY em.nome").append("\n");

		final Query query = this.entityManager.createNativeQuery(sql.toString(), Pessoa.class);

		if (StringUtils.isNotBlank(f.getNome())) {
			query.setParameter("nome", this.like(f.getNome()));
		}
		if (StringUtils.isNotBlank(f.getCpfCnpj())) {
			query.setParameter("cpfCnpj", f.getCpfCnpj());
		}
		if (ObjectUtils.isNotEmpty(f.getPessoaTipo())) {
			query.setParameter("tipo", f.getPessoaTipo().stream().map(v -> v.name()).collect(Collectors.toSet()));
		}

		return query.getResultList();
	}

}
