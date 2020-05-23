package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.OauthAccessTokenBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.OauthAccessTokenFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.OauthAccessToken;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "oauth-access-token")
public class OauthAccessTokenCRUDREST
		extends CRUDREST<OauthAccessToken, java.lang.String, OauthAccessTokenFiltroDTO, OauthAccessTokenBO> {

	public OauthAccessTokenCRUDREST(@Autowired final OauthAccessTokenBO bo) {
		super(bo);
	}

}
