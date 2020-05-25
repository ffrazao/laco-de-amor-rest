package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "EventoProduto")
@Table(schema = "laco_de_amor", name = "evento_produto")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class EventoProduto extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "evento_id")
	@JsonIgnore
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "evento_pessoa_id")
	private EventoPessoa eventoPessoa;

	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;

	@ManyToOne
	@JoinColumn(name = "unidade_medida_id")
	private UnidadeMedida unidadeMedida;
	
	@Column(name = "quantidade")
	private BigDecimal quantidade;
	
	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Override
	public String toString() {
		return String.format("Id = %d", this.getId());
	}

}
