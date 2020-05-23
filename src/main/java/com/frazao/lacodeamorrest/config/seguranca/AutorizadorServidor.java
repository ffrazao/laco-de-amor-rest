package com.frazao.lacodeamorrest.config.seguranca;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAuthorizationServer
@EnableTransactionManagement
public class AutorizadorServidor extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private DataSource datasource;

	@Autowired
	private TokenEnhancer tokenEnhancer;

	@Bean
	public ApprovalStore approvalStore() {
		return new JdbcApprovalStore(this.datasource);
	}

	@Bean
	public AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(this.datasource);
	}

	public JdbcClientDetailsService clientDetailsService() {
		return new JdbcClientDetailsService(this.datasource);
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager).approvalStore(this.approvalStore())
				.authorizationCodeServices(this.authorizationCodeServices()).tokenStore(this.tokenStore())
				.tokenEnhancer(this.tokenEnhancer);
	}

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(this.clientDetailsService());
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(this.datasource);
	}

	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
