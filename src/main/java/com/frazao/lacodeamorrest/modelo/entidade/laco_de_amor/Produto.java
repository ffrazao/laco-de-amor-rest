package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Produto")
@Table(schema = "laco_de_amor", name = "produto")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Produto extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Transient
	private List<EventoProduto> eventoProdutoList;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_modelo_id")
	private com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Produto produtoModeloId;

}