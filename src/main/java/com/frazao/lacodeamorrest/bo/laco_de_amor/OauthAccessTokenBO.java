package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.OauthAccessTokenDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.OauthAccessTokenFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.OauthAccessToken;

@Service
public class OauthAccessTokenBO extends CRUDBO<OauthAccessToken, java.lang.String, OauthAccessTokenFiltroDTO> {

	public OauthAccessTokenBO(@Autowired final OauthAccessTokenDAO dao) {
		super(OauthAccessToken.class, dao);
	}

	@Override
	public OauthAccessTokenDAO getDAO() {
		return (OauthAccessTokenDAO) super.getDAO();
	}

}
