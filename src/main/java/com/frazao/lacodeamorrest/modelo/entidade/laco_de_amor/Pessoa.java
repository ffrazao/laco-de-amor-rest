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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.frazao.lacodeamorrest.modelo.dominio.PessoaTipo;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Pessoa")
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Pessoa extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private Parceiro parceiro;
	
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private Cliente cliente;
	
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private Fornecedor fornecedor;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<PessoaEndereco> pessoaEnderecoList;

	@Column(name = "contato1")
	private String contato1;
	
	@Column(name = "contato2")
	private String contato2;
	
	@Column(name = "contato3")
	private String contato3;
	
	@Column(name = "cpf_cnpj")
	private String cpfCnpj;
	
	@Column(name = "email")
	private String email;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private PessoaTipo tipo;

}
