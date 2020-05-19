package com.frazao.lacodeamorrest.config.seguranca;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.frazao.lacodeamorrest.bo.UsuarioBO;
import com.frazao.lacodeamorrest.modelo.entidade.Usuario;

@Component
public class TokenEnhancerConfig implements TokenEnhancer {

	public TokenEnhancerConfig() {
	}
	
	@Autowired
	private UsuarioBO bo;

	@SuppressWarnings("unchecked")
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		DefaultOAuth2AccessToken tempResult = (DefaultOAuth2AccessToken) accessToken;

		Map<String, Object> details = new HashMap<>();
		Object userDetails = null;
		if (authentication.getUserAuthentication() != null && authentication.getUserAuthentication().getDetails() != null) {
			userDetails = authentication.getUserAuthentication().getDetails();
		}
		if (userDetails != null) {
			details = (Map<String, Object>) userDetails;
		}
		
		Usuario usuario = this.bo.findByLogin(authentication.getUserAuthentication().getName());
		
		details.put("nome", usuario.getPessoa().isPresent() ? usuario.getPessoa().get().getNome() : usuario.getLogin());
		details.put("foto", usuario.getFoto());
		details.put("email", usuario.getEmail());
		details.put("perfil", usuario.getPerfil());

		tempResult.setAdditionalInformation(details);

		return tempResult;
	}

}
