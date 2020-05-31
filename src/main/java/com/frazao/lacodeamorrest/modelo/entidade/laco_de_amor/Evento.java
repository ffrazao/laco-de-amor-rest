package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Evento")
@Table(name = "evento")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
public class Evento extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Column(name = "data")
	private LocalDateTime data;

	@OneToMany(mappedBy = "evento")
	private Collection<EventoPessoa> eventoPessoaList = new ArrayList<>();

	@OneToMany(mappedBy = "evento")
	private Collection<EventoProduto> eventoProdutoList = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "evento_tipo_id")
	private EventoTipo eventoTipo;

	@OneToMany(mappedBy = "pai")
	@JsonIgnore
	private Collection<Evento> filhoList = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pai_id", insertable = false, updatable = false)
	private Evento pai;

	@Column(name = "pai_id")
	private Integer paiId;

	public Evento(final Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return String.format("Id = %d", this.getId());
	}

}
