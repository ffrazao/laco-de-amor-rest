package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// @Entity(name = "OauthApprovals")
// @Table(schema = "laco_de_amor", name = "oauth_approvals")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class OauthApprovals implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "clientid")
	private String clientid;

	@Column(name = "expiresat")
	@Basic
	private java.time.LocalDateTime expiresat;

	@Column(name = "lastmodifiedat")
	@Basic
	private java.time.LocalDateTime lastmodifiedat;

	@Column(name = "scope")
	private String scope;

	@Column(name = "status")
	private String status;

	@Column(name = "userid")
	private String userid;
	
	@Override
	public String toString() {
		return String.format("ClientId = %d", this.getClientid());
	}

}
