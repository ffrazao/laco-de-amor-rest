package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "OauthClientDetails")
@Table(schema = "laco_de_amor", name = "oauth_client_details")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OauthClientDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "access_token_validity")
	private Integer accessTokenValidity;

	@Column(name = "additional_information")
	private String additionalInformation;

	@Column(name = "authorities")
	private String authorities;

	@Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;

	@Column(name = "autoapprove")
	private String autoapprove;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private String clientId;

	@Column(name = "client_secret")
	private String clientSecret;

	@Column(name = "refresh_token_validity")
	private Integer refreshTokenValidity;

	@Column(name = "resource_ids")
	private String resourceIds;

	@Column(name = "scope")
	private String scope;

	@Column(name = "web_server_redirect_uri")
	private String webServerRedirectUri;

	@Override
	public String toString() {
		return String.format("ClientId = %d", this.getClientId());
	}

}
