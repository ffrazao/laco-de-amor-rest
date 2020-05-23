package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.OauthClientTokenBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.OauthClientTokenFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.OauthClientToken;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "oauth-client-token")
public class OauthClientTokenCRUDREST
		extends CRUDREST<OauthClientToken, java.lang.String, OauthClientTokenFiltroDTO, OauthClientTokenBO> {

	public OauthClientTokenCRUDREST(@Autowired final OauthClientTokenBO bo) {
		super(bo);
	}

}
