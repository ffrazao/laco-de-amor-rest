package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "ProdutoDescricao")
@Table(schema = "laco_de_amor", name = "produto_descricao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProdutoDescricao extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "ordem")
	private Integer ordem;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_atributo_id")
	private ProdutoAtributo produtoAtributo;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "produto_modelo_id")
	private ProdutoModelo produtoModelo;

	@Column(name = "valor")
	@Lob
	private String valor;

	public ProdutoDescricao(final Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return String.format("%s", this.id);
	}

}
