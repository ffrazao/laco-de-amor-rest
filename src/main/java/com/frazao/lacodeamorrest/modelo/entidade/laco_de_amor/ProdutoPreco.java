package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@Entity(name = "ProdutoPreco")
@Table(schema = "laco_de_amor", name = "produto_preco")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class ProdutoPreco extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Column(name = "destinacao")
	@Enumerated(EnumType.STRING)
	private com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.ProdutoPrecoDestinacao destinacao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_modelo_id")
	private com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoPreco produtoModeloId;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "vigencia")
	@Basic
	private java.time.LocalDateTime vigencia;

}
