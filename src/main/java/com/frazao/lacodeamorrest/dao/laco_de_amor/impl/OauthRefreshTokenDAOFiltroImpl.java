package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;

import com.frazao.lacodeamorrest.dao.laco_de_amor.OauthRefreshTokenDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.OauthRefreshTokenFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.OauthRefreshToken;

public class OauthRefreshTokenDAOFiltroImpl implements OauthRefreshTokenDAOFiltro {

	@Value("${default.database_schema}")
	private String databaseSchema;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<OauthRefreshToken> filtrar(final OauthRefreshTokenFiltroDTO f) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT em.*").append("\n");
		sql.append("FROM   ").append(this.databaseSchema).append(".oauth_refresh_token as em").append("\n");
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
		// if (StringUtils.isNotBlank(f.getCpfCnpj())) {
		// arg.append(adWhere(arg)).append("em.cpf_cnpj = :cpfCnpj").append("\n");
		// }
		// if (ObjectUtils.isNotEmpty(f.getTipo())) {
		// arg.append(adWhere(arg)).append("em.pessoa_tipo in :tipo").append("\n");
		// }
		sql.append(arg);
		sql.append("ORDER BY 1").append("\n");
		final Query query = this.entityManager.createNativeQuery(sql.toString(), OauthRefreshToken.class);
		if (ObjectUtils.isNotEmpty(idSim)) {
			query.setParameter("idSim", new HashSet<>(Arrays.asList(idSim)));
		}
		if (ObjectUtils.isNotEmpty(idNao)) {
			query.setParameter("idNao", new HashSet<>(Arrays.asList(idNao)));
		}
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
