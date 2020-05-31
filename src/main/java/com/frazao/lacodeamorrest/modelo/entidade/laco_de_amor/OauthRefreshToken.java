package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Lob;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// @Entity(name = "OauthRefreshToken")
// @Table(schema = "laco_de_amor", name = "oauth_refresh_token")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OauthRefreshToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "authentication")
	@Lob
	private byte[] authentication;

	@Column(name = "token")
	@Lob
	private byte[] token;

	@Column(name = "token_id")
	private String tokenId;

	@Override
	public String toString() {
		return String.format("Token = %d", this.getToken());
	}

}
