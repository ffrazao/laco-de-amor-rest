package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.ParceiroFuncao;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Parceiro")
@Table(name = "parceiro")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Parceiro extends EntidadeBaseTemId<Integer> {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private Integer id;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "id")
	private Pessoa pessoa;
	
    @Enumerated(EnumType.STRING)
    @Column(name="funcao")
    private ParceiroFuncao funcao;

}
