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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.PessoaTipo;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;
import com.frazao.lacodeamorrest.modelo.validador.PessoaFisica;
import com.frazao.lacodeamorrest.modelo.validador.PessoaJuridica;
import com.frazao.lacodeamorrest.modelo.validador.PessoaTipoGroupSequenceProvider;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Pessoa")
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@GroupSequenceProvider(value = PessoaTipoGroupSequenceProvider.class)
public class Pessoa extends EntidadeBaseTemId<Integer> {

	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private Cliente cliente;

	@Column(name = "contato1")
	@Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
	private String contato1;

	@Column(name = "contato2")
	@Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
	private String contato2;

	@Column(name = "contato3")
	@Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
	private String contato3;

	@Column(name = "cpf_cnpj")
	@CPF(groups = PessoaFisica.class)
	@CNPJ(groups = PessoaJuridica.class)
	private String cpfCnpj;

	@Column(name = "email")
	@Email
	private String email;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private Fornecedor fornecedor;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	@NotBlank
	private String nome;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private Parceiro parceiro;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PessoaEndereco> pessoaEnderecoList;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	@NotNull
	private PessoaTipo pessoaTipo;

	public Pessoa(final Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return String.format("Id = %d", this.getId());
	}

}
