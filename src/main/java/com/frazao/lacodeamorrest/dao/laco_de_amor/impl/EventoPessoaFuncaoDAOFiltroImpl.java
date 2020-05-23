package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoPessoaFuncaoDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoPessoaFuncaoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoPessoaFuncao;

public class EventoPessoaFuncaoDAOFiltroImpl implements EventoPessoaFuncaoDAOFiltro {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<EventoPessoaFuncao> filtrar(final EventoPessoaFuncaoFiltroDTO f) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT em.*").append("\n");
		sql.append("FROM   laco_de_amor.evento_pessoa_funcao as em").append("\n");
		final StringBuilder arg = new StringBuilder();
		// if (StringUtils.isNotBlank(f.getCpfCnpj())) {
		// arg.append(adWhere(arg)).append("em.cpf_cnpj = :cpfCnpj").append("\n");
		// }
		// if (ObjectUtils.isNotEmpty(f.getTipo())) {
		// arg.append(adWhere(arg)).append("em.pessoa_tipo in :tipo").append("\n");
		// }
		sql.append(arg);
		sql.append("ORDER BY 1").append("\n");
		final Query query = this.entityManager.createNativeQuery(sql.toString(), EventoPessoaFuncao.class);
		// if (StringUtils.isNotBlank(f.getCpfCnpj())) {
		// query.setParameter("cpfCnpj", f.getCpfCnpj());
		// }
		// if (ObjectUtils.isNotEmpty(f.getTipo())) {
		// query.setParameter("tipo", f.getTipo().stream().map(v ->
		// v.name()).collect(Collectors.toSet()));
		// }
		return query.getResultList();

	}

}
