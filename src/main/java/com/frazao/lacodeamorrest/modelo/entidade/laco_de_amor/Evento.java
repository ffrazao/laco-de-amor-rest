package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Evento")
@Table(name = "evento")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public abstract class Evento extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Transient
	private List<Comprar> comprarList;

	@Transient
	private List<Cotar> cotarList;

	@Column(name = "data")
	private Calendar data;

	@Transient
	private List<Evento> eventoList;

	@Transient
	private List<EventoPessoa> eventoPessoaList;

	@Transient
	private List<EventoProduto> eventoProdutoList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evento_tipo_id")
	private com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Evento eventoTipoId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pai_id")
	private com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Evento paiId;

	@Transient
	private List<Produzir> produzirList;

	@Transient
	private List<Utilizar> utilizarList;

	@Transient
	private List<Vender> venderList;

}
