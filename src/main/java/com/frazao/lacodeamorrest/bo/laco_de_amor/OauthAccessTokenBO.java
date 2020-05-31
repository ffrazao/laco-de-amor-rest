package com.frazao.lacodeamorrest.bo.laco_de_amor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUD;
import com.frazao.lacodeamorrest.dao.laco_de_amor.OauthAccessTokenDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.OauthAccessTokenFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.OauthAccessToken;

@Service
public class OauthAccessTokenBO
		implements CRUD<OauthAccessToken, String, OauthAccessTokenFiltroDTO, OauthAccessTokenDAO> {

	private final OauthAccessTokenDAO dao;

	private final Class<OauthAccessToken> entidadeClasse;

	public OauthAccessTokenBO(@Autowired final OauthAccessTokenDAO dao) {
		this.dao = dao;
		this.entidadeClasse = OauthAccessToken.class;
	}

	@Override
	public String create(final OauthAccessToken t) throws BOException {
		final OauthAccessToken result = this.getDao().save(t);
		return result.getClientId();
	}

	@Override
	public void delete(final String id) throws BOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<OauthAccessToken> filter(final OauthAccessTokenFiltroDTO filtro) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	public OauthAccessTokenDAO getDao() {
		return this.dao;
	}

	@Override
	public OauthAccessTokenDAO getDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<OauthAccessToken> getEntidadeClasse() {
		return this.entidadeClasse;
	}

	@Override
	public OauthAccessToken novo(final OauthAccessToken modelo) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OauthAccessToken restore(final String id) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OauthAccessToken update(final String id, final OauthAccessToken t) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

}
