package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.time.LocalDateTime;
import java.util.List;

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
public class Evento extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "data")
	private LocalDateTime data;
	
	@ManyToOne
	@JoinColumn(name = "evento_tipo_id")
	private EventoTipo eventoTipo;

	@OneToMany(mappedBy = "evento")
	private List<EventoPessoa> eventoPessoaList;

	@OneToMany(mappedBy = "evento")
	private List<EventoProduto> eventoProdutoList;

	@ManyToOne
	@JoinColumn(name = "pai_id")
	private Evento pai;
	
	@OneToMany(mappedBy = "pai")
	private List<Evento> filhoList;

	@Override
	public String toString() {
		return String.format("Id = %d", this.getId());
	}

}
