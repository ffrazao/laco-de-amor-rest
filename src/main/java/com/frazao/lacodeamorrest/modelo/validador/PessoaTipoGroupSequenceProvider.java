package com.frazao.lacodeamorrest.modelo.validador;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.frazao.lacodeamorrest.modelo.dominio.laco_de_amor.PessoaTipo;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Pessoa;

public class PessoaTipoGroupSequenceProvider implements DefaultGroupSequenceProvider<Pessoa> {

	public List<Class<?>> getValidationGroups(Pessoa entity) {
		List<Class<?>> groups = new ArrayList<>();

		/*
		 * Informamos ao HibernateValidator para usar as validações default definidas na
		 * classe Cliente.
		 */
		groups.add(Pessoa.class);

		if (entity != null) {
			/*
			 * Aqui nós implementamos a lógica que determina o grupo de validação a ser
			 * aplicado ao bean.
			 */
			if (PessoaTipo.PF.equals(entity.getPessoaTipo())) {
				groups.add(PessoaFisica.class);
			} else if (PessoaTipo.PJ.equals(entity.getPessoaTipo())) {
				groups.add(PessoaJuridica.class);
			}
		}

		return groups;
	}

}
