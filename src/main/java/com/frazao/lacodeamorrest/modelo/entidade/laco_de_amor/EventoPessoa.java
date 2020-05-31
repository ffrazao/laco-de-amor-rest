package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "EventoPessoa")
@Table(schema = "laco_de_amor", name = "evento_pessoa")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
public class EventoPessoa extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "evento_id")
	@JsonIgnore
	private Evento evento;

	@ManyToOne
	@JoinColumn(name = "evento_pessoa_funcao_id")
	private EventoPessoaFuncao eventoPessoaFuncao;

	@OneToMany(mappedBy = "eventoPessoa")
	private Collection<EventoProduto> eventoProdutoList = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	public EventoPessoa(final Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return String.format("Id = %d", this.getId());
	}

}
