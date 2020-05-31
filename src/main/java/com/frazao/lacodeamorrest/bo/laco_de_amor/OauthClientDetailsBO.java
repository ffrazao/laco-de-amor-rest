package com.frazao.lacodeamorrest.bo.laco_de_amor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUD;
import com.frazao.lacodeamorrest.dao.laco_de_amor.OauthClientDetailsDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.OauthClientDetailsFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.OauthClientDetails;

@Service
public class OauthClientDetailsBO
		implements CRUD<OauthClientDetails, String, OauthClientDetailsFiltroDTO, OauthClientDetailsDAO> {

	private final OauthClientDetailsDAO dao;

	private final Class<OauthClientDetails> entidadeClasse;

	public OauthClientDetailsBO(@Autowired final OauthClientDetailsDAO dao) {
		this.dao = dao;
		this.entidadeClasse = OauthClientDetails.class;
	}

	@Override
	public String create(final OauthClientDetails t) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(final String id) throws BOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<OauthClientDetails> filter(final OauthClientDetailsFiltroDTO filtro) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	public OauthClientDetailsDAO getDao() {
		return this.dao;
	}

	@Override
	public OauthClientDetailsDAO getDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<OauthClientDetails> getEntidadeClasse() {
		return this.entidadeClasse;
	}

	@Override
	public OauthClientDetails novo(final OauthClientDetails modelo) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OauthClientDetails restore(final String id) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OauthClientDetails update(final String id, final OauthClientDetails t) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

}
