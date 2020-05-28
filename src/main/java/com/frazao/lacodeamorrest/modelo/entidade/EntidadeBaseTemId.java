package com.frazao.lacodeamorrest.modelo.entidade;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class EntidadeBaseTemId<Id> extends EntidadeBase implements TemId<Id> {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return String.format("Id = %d", this.getId());
	}
	
	public EntidadeBaseTemId(Id id) {
		super();
		this.setId(id);
	}

}
