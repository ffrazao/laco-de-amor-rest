package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.OauthClientDetailsDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.OauthClientDetailsFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.OauthClientDetails;

@Service
public class OauthClientDetailsBO extends CRUDBO<OauthClientDetails, java.lang.String, OauthClientDetailsFiltroDTO> {

	public OauthClientDetailsBO(@Autowired final OauthClientDetailsDAO dao) {
		super(OauthClientDetails.class, dao);
	}

	@Override
	public OauthClientDetailsDAO getDAO() {
		return (OauthClientDetailsDAO) super.getDAO();
	}

}
