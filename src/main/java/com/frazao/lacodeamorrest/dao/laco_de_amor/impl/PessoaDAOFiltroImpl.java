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
import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.PessoaVinculoTipo;
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

		if (ObjectUtils.isNotEmpty(f.getPessoaVinculoTipo())
				&& f.getPessoaVinculoTipo().size() != PessoaVinculoTipo.values().length) {
			if (f.getPessoaVinculoTipo().contains(PessoaVinculoTipo.CLIENTE)) {
				sql.append("LEFT JOIN ").append(databaseSchema).append(".cliente as cli").append("\n");
				sql.append("ON        cli.id = em.id").append("\n");
			}
			if (f.getPessoaVinculoTipo().contains(PessoaVinculoTipo.FORNECEDOR)) {
				sql.append("LEFT JOIN ").append(databaseSchema).append(".fornecedor as frn").append("\n");
				sql.append("ON        frn.id = em.id").append("\n");
			}
			if (f.getPessoaVinculoTipo().contains(PessoaVinculoTipo.PARCEIRO)) {
				sql.append("LEFT JOIN ").append(databaseSchema).append(".parceiro as par").append("\n");
				sql.append("ON        par.id = em.id").append("\n");
			}
		}

		final StringBuilder arg = new StringBuilder();

		// condicao ou
		final StringBuilder arg1 = new StringBuilder();
		if (StringUtils.isNotBlank(f.getNome())) {
			arg1.append(this.adOr(arg1)).append("(em.nome like :nome)").append("\n");
		}
		if (StringUtils.isNotBlank(f.getCpfCnpj())) {
			arg1.append(this.adOr(arg1)).append("(em.cpf_cnpj = :cpfCnpj)").append("\n");
		}
		if (arg1.length() > 0) {
			arg.append(adWhere(arg)).append("(").append(arg1).append(")").append("\n");
		}

		if (ObjectUtils.isNotEmpty(f.getPessoaTipo())) {
			arg.append(this.adWhere(arg)).append("em.tipo in :tipo").append("\n");
		}
		if (ObjectUtils.isNotEmpty(f.getPessoaVinculoTipo())
				&& f.getPessoaVinculoTipo().size() != PessoaVinculoTipo.values().length) {
			final StringBuilder arg2 = new StringBuilder();

			if (f.getPessoaVinculoTipo().contains(PessoaVinculoTipo.CLIENTE)) {
				arg2.append(this.adOr(arg2)).append("(cli.id is not null)").append("\n");
			}
			if (f.getPessoaVinculoTipo().contains(PessoaVinculoTipo.FORNECEDOR)) {
				arg2.append(this.adOr(arg2)).append("(frn.id is not null)").append("\n");
			}
			if (f.getPessoaVinculoTipo().contains(PessoaVinculoTipo.PARCEIRO)) {
				arg2.append(this.adOr(arg2)).append("(par.id is not null)").append("\n");
			}
			if (arg2.length() > 0) {
				arg.append(adWhere(arg)).append("(").append(arg2).append(")").append("\n");
			}
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
