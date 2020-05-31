package com.frazao.lacodeamorrest.bo.laco_de_amor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUD;
import com.frazao.lacodeamorrest.dao.laco_de_amor.OauthClientTokenDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.OauthClientTokenFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.OauthClientToken;

@Service
public class OauthClientTokenBO
		implements CRUD<OauthClientToken, String, OauthClientTokenFiltroDTO, OauthClientTokenDAO> {

	private final OauthClientTokenDAO dao;

	private final Class<OauthClientToken> entidadeClasse;

	public OauthClientTokenBO(@Autowired final OauthClientTokenDAO dao) {
		this.dao = dao;
		this.entidadeClasse = OauthClientToken.class;
	}

	@Override
	public String create(final OauthClientToken t) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(final String id) throws BOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<OauthClientToken> filter(final OauthClientTokenFiltroDTO filtro) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	public OauthClientTokenDAO getDao() {
		return this.dao;
	}

	@Override
	public OauthClientTokenDAO getDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<OauthClientToken> getEntidadeClasse() {
		return this.entidadeClasse;
	}

	@Override
	public OauthClientToken novo(final OauthClientToken modelo) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OauthClientToken restore(final String id) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OauthClientToken update(final String id, final OauthClientToken t) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

}
