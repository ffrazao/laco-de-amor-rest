package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "UnidadeMedida")
@Table(schema = "laco_de_amor", name = "unidade_medida")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class UnidadeMedida extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Column(name = "base")
	@Enumerated(EnumType.STRING)
	private Confirmacao base;

	@Column(name = "codigo")
	private String codigo;

	@Transient
	private List<EventoProduto> eventoProdutoList;

	@OneToMany(mappedBy = "pai")
	private List<UnidadeMedida> filhoList;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "pai_id", insertable = false, updatable = false)
	@JsonIgnore
	private UnidadeMedida pai;

	@Column(name = "pai_id")
	private Integer paiId;

	@Column(name = "valor_base")
	private BigDecimal valorBase;

	public UnidadeMedida(final Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return String.format("Id = %d", this.getId());
	}

}
