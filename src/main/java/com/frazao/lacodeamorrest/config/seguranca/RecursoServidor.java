package com.frazao.lacodeamorrest.config.seguranca;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Created by Frazão
 */
@EnableResourceServer
@Configuration
public class RecursoServidor extends ResourceServerConfigurerAdapter {

	@Autowired
	private DataSource datasource;

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.anonymous().and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/usuario/recuperar-senha", "/usuario/autorizar-trocar-senha", "/usuario/trocar-senha", "/ping").permitAll()
				.antMatchers(HttpMethod.GET, "/produto-modelo/imagem-venda").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers("/**").authenticated().and()

//			.requestMatchers()
//				.antMatchers("/**").and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
				.antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')");
	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
		final TokenStore tokenStore = new JdbcTokenStore(this.datasource);
		resources.resourceId("laco-de-amor.api").tokenStore(tokenStore);
	}

}