package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Evento;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Vender;

public class EventoDAOFiltroImpl implements EventoDAOFiltro {

	@Value("${default.database_schema}")
	private String databaseSchema;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Evento> filtrar(final EventoFiltroDTO f) {

		return this.filtrar("evento", Evento.class, f).getResultList();

	}

	@Override
	public Query filtrar(final String tabela, final Class<? extends Evento> clazz, final EventoFiltroDTO f) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT       DISTINCT em.*").append("\n");
		if (clazz.isAssignableFrom(Vender.class)) {
			sql.append("             , sub.endereco_id").append("\n");
		}
		sql.append("FROM         ").append(databaseSchema).append(".evento as em").append("\n");
		sql.append("INNER JOIN   ").append(databaseSchema).append(".").append(tabela).append("  as sub").append("\n");
		sql.append("ON           em.id = sub.id").append("\n");

		if (StringUtils.isNotBlank(f.getProduto())) {
			sql.append("LEFT JOIN    laco_de_amor.evento_produto as epr").append("\n");
			sql.append("ON           epr.evento_id = em.id").append("\n");
			sql.append("LEFT JOIN    laco_de_amor.produto pr").append("\n");
			sql.append("ON           pr.id = epr.produto_id").append("\n");
			sql.append("LEFT JOIN    laco_de_amor.produto_modelo prm").append("\n");
			sql.append("ON           prm.id = pr.produto_modelo_id").append("\n");
		}

		if (StringUtils.isNotBlank(f.getParticipante())) {
			sql.append("LEFT JOIN    laco_de_amor.evento_pessoa as epe").append("\n");
			sql.append("ON           epe.evento_id = em.id").append("\n");
			sql.append("LEFT JOIN    laco_de_amor.pessoa pe").append("\n");
			sql.append("ON           pe.id = epe.pessoa_id").append("\n");
		}

		final StringBuilder arg = new StringBuilder();
		if (ObjectUtils.isNotEmpty(f.getDataInicio())) {
			arg.append(adWhere(arg)).append("em.data >= :dataInicio").append("\n");
		}
		if (ObjectUtils.isNotEmpty(f.getDataTermino())) {
			arg.append(adWhere(arg)).append("em.data <= :dataTermino").append("\n");
		}
		if (StringUtils.isNotBlank(f.getProduto())) {
			arg.append(adWhere(arg)).append("(prm.codigo like :produto or prm.nome like :produto)").append("\n");
		}
		if (StringUtils.isNotBlank(f.getParticipante())) {
			arg.append(adWhere(arg)).append("(pe.cpf_cnpj like :participante or pe.nome like :participante)")
					.append("\n");
		}
		if (Confirmacao.S.equals(f.getUtilizado())) {
			arg.append(adWhere(arg)).append("em.id in (select pai_id from evento where pai_id is not null)").append("\n");
		} else if (Confirmacao.N.equals(f.getUtilizado())) {
			arg.append(adWhere(arg)).append("em.id not in (select pai_id from evento where pai_id is not null)").append("\n");
		}

		sql.append(arg);
		sql.append("ORDER BY em.data DESC").append("\n");
		final Query query = this.entityManager.createNativeQuery(sql.toString(), clazz);
		if (ObjectUtils.isNotEmpty(f.getDataInicio())) {
			query.setParameter("dataInicio", f.getDataInicio());
		}
		if (ObjectUtils.isNotEmpty(f.getDataTermino())) {
			query.setParameter("dataTermino", f.getDataTermino());
		}
		if (StringUtils.isNotBlank(f.getProduto())) {
			query.setParameter("produto", like(f.getProduto()));
		}
		if (StringUtils.isNotBlank(f.getParticipante())) {
			query.setParameter("participante", like(f.getParticipante()));
		}

		return query;

	}

}
