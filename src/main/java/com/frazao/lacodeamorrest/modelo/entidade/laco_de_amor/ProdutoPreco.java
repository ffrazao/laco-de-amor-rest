package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.ProdutoPrecoDestinacao;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "destinacao")
	@Enumerated(EnumType.STRING)
	private ProdutoPrecoDestinacao destinacao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "produto_modelo_id")
	private ProdutoModelo produtoModelo;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "vigencia")
	private LocalDateTime vigencia;

	@Override
	public String toString() {
		return String.format("%s", this.id);
	}

}
