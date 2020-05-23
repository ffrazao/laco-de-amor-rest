package com.frazao.lacodeamorrest.rest.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.OauthClientDetailsBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.OauthClientDetailsFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.OauthClientDetails;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "oauth-client-details")
public class OauthClientDetailsCRUDREST
		extends CRUDREST<OauthClientDetails, java.lang.String, OauthClientDetailsFiltroDTO, OauthClientDetailsBO> {

	public OauthClientDetailsCRUDREST(@Autowired final OauthClientDetailsBO bo) {
		super(bo);
	}

}
