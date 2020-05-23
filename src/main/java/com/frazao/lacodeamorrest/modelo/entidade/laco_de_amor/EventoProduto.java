package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evento_id")
	private com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoProduto eventoId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evento_pessoa_id")
	private com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoProduto eventoPessoaId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id")
	private com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoProduto produtoId;

	@Column(name = "quantidade")
	private BigDecimal quantidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidade_medida_id")
	private com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.EventoProduto unidadeMedidaId;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;

}
