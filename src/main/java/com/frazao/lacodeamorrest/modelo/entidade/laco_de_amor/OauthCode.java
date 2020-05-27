package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Lob;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// @Entity(name = "OauthCode")
// @Table(schema = "laco_de_amor", name = "oauth_code")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class OauthCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "authentication")
	@Lob
	private byte[] authentication;

	@Column(name = "code")
	private String code;
	
	@Override
	public String toString() {
		return String.format("Code = %d", this.getCode());
	}

}
