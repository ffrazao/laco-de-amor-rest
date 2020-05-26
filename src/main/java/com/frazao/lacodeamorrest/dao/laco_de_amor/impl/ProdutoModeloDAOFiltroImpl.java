package com.frazao.lacodeamorrest.dao.laco_de_amor.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoModeloDAOFiltro;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoModeloFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoModelo;

public class ProdutoModeloDAOFiltroImpl implements ProdutoModeloDAOFiltro {

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${default.database_schema}")
	private String databaseSchema;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProdutoModelo> filtrar(final ProdutoModeloFiltroDTO f) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT em.*").append("\n");
		sql.append("FROM   ").append(databaseSchema).append(".produto_modelo as em").append("\n");
		final StringBuilder arg = new StringBuilder();
		
		// condicao ou
		final StringBuilder arg1 = new StringBuilder();
		if (StringUtils.isNotBlank(f.getNome())) {
			arg1.append(adOr(arg1)).append("(em.nome like :nome)").append("\n");
		}
		if (StringUtils.isNotBlank(f.getCodigo())) {
			arg1.append(adOr(arg1)).append("(em.codigo like :codigo)").append("\n");
		}
		if (arg1.length() > 0) {			
			arg.append(adWhere(arg)).append("(").append(arg1).append(")") .append("\n");
		}
		
		if (ObjectUtils.isNotEmpty(f.getMateriaPrima())) {
			arg.append(adWhere(arg)).append("em.materia_prima in :materiaPrima").append("\n");
		}
		sql.append(arg);
		sql.append("ORDER BY em.nome").append("\n");
		final Query query = this.entityManager.createNativeQuery(sql.toString(), ProdutoModelo.class);
		if (StringUtils.isNotBlank(f.getNome())) {
			query.setParameter("nome", like(f.getNome()));
		}
		if (StringUtils.isNotBlank(f.getCodigo())) {
			query.setParameter("codigo", like(f.getCodigo()));
		}
		if (ObjectUtils.isNotEmpty(f.getMateriaPrima())) {
			query.setParameter("materiaPrima", f.getMateriaPrima().stream().map(v -> v.name()).collect(Collectors.toSet()));
		}
		return query.getResultList();

	}

}
