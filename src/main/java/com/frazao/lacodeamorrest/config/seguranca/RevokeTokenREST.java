package com.frazao.lacodeamorrest.config.seguranca;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RevokeTokenREST {
	
	@Autowired
    private TokenStore tokenStore;
	
	 @PostMapping("/oauth/logout")
	 public ResponseEntity<Boolean> revoke(HttpServletRequest request) {
	     try {
	         String authorization = request.getHeader("Authorization");
	         if (authorization != null && authorization.contains("Bearer")) {
	             String tokenValue = authorization.replace("Bearer", "").trim();

	             OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
	             if (accessToken != null) {
	            	 tokenStore.removeAccessToken(accessToken);
	            	 
	            	 //OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(tokenValue);
	            	 OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
	            	 if (refreshToken != null) {	            	 
	            		 tokenStore.removeRefreshToken(refreshToken);
	            	 }
	             }
	         }
	     } catch (Exception e) {
	         return ResponseEntity.badRequest().body(false);
	     }

	     return ResponseEntity.ok().body(true);
	 }

}
