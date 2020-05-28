package com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "ProdutoModelo")
@Table(schema = "laco_de_amor", name = "produto_modelo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProdutoModelo extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "foto")
	@Lob
	private byte[] foto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "materia_prima")
	@Enumerated(EnumType.STRING)
	private Confirmacao materiaPrima;

	@Column(name = "nome")
	private String nome;

	@OneToMany(mappedBy = "produtoModelo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProdutoDescricao> produtoDescricaoList;

	@OneToMany(mappedBy = "produtoModelo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProdutoPreco> produtoPrecoList;

	public ProdutoModelo(final Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return String.format("%s", this.id);
	}

}
