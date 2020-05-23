package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.OauthClientTokenDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.OauthClientTokenFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.OauthClientToken;

@Service
public class OauthClientTokenBO extends CRUDBO<OauthClientToken, java.lang.String, OauthClientTokenFiltroDTO> {

	public OauthClientTokenBO(@Autowired final OauthClientTokenDAO dao) {
		super(OauthClientToken.class, dao);
	}

	@Override
	public OauthClientTokenDAO getDAO() {
		return (OauthClientTokenDAO) super.getDAO();
	}

}
