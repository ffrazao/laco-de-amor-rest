package com.frazao.lacodeamorrest.modelo.entidade;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public abstract class EntidadeBaseTemId<Id> extends EntidadeBase implements TemId<Id> {

	private static final long serialVersionUID = 1L;
	
}
