package com.frazao.lacodeamorrest.bo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frazao.lacodeamorrest.dao.Filtro;
import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBase;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;
import com.frazao.lacodeamorrest.modelo.entidade.TemId;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public abstract class CRUDBO<E, Id, F extends FiltroDTO> implements BO {

	private final Class<E> entidadeClasse;

	private final JpaRepository<E, Id> dao;

	public CRUDBO(final Class<E> clazz, final JpaRepository<E, Id> dao) {
		if (CRUDBO.log.isDebugEnabled()) {
			CRUDBO.log.debug("Novo BO ({})", clazz);
		}
		this.entidadeClasse = clazz;
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Id create(final E t) {
		if (CRUDBO.log.isDebugEnabled()) {
			CRUDBO.log.debug("Criando ({})..", t);
		}

		// adicionar link reverso
		this.vinculaOneToMany(t);
		this.vinculaOneToOne(t);

		this.getDAO().save(t);
		if (CRUDBO.log.isTraceEnabled()) {
			CRUDBO.log.trace("Criado ({})..", t);
		}
		return ((TemId<Id>) t).getId();
	}

	@Transactional
	public void delete(final Id id) {
		if (CRUDBO.log.isDebugEnabled()) {
			CRUDBO.log.debug("Excluindo ({})..", id);
		}
		this.getDAO().deleteById(id);
		if (CRUDBO.log.isTraceEnabled()) {
			CRUDBO.log.trace("Excluido ({})..", id);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<E> filter(final F filtro) {
		if (CRUDBO.log.isDebugEnabled()) {
			CRUDBO.log.debug("Filtrando ({})..", filtro);
		}
		final Collection<E> result = ((Filtro<E, F>) this.getDAO()).filtrar(filtro);
		if (CRUDBO.log.isTraceEnabled()) {
			CRUDBO.log.trace("Filtrado ({})..", result);
		}
		return result;
	}

	public JpaRepository<E, Id> getDAO() {
		return this.dao;
	}

	public E instantiate() {
		try {
			if (CRUDBO.log.isTraceEnabled()) {
				CRUDBO.log.trace("Nova instancia ({})..", this.entidadeClasse);
			}
			return this.entidadeClasse.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public E novo(final E modelo) {
		if (CRUDBO.log.isDebugEnabled()) {
			CRUDBO.log.debug("Criando novo ({})..", modelo);
		}
		final E result = modelo == null ? this.instantiate() : modelo;
		if (CRUDBO.log.isTraceEnabled()) {
			CRUDBO.log.trace("Criado novo ({})..", result);
		}
		return result;
	}

	public E restore(final Id id) {
		if (CRUDBO.log.isDebugEnabled()) {
			CRUDBO.log.debug("Recuperando ({})..", id);
		}
		final E result = this.getDAO().getOne(id);
		if (CRUDBO.log.isDebugEnabled()) {
			CRUDBO.log.debug("Recuperado ({})..", result);
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public E update(final Id id, final E t) {
		if (CRUDBO.log.isDebugEnabled()) {
			CRUDBO.log.debug("Salvando ({})..", id);
		}
		((TemId<Id>) t).setId(id);

		// adicionar link reverso
		this.vinculaOneToMany(t);
		this.vinculaOneToOne(t);

		final E result = this.getDAO().save(t);

		if (CRUDBO.log.isTraceEnabled()) {
			CRUDBO.log.trace("Salvo ({})..", result);
		}
		return result;
	}

	protected void vinculaOneToMany(final E t) {
		for (final Field field : getAllFields(t.getClass())) {
			if (Collection.class.isAssignableFrom(field.getType())) {
				try {
					final boolean origem = field.isAccessible();
					field.setAccessible(true);
					if (field.get(t) != null) {
						for (final Object linha : (Collection<?>) field.get(t)) {
							for (final Field subField : getAllFields(linha.getClass())) {
								// verificar classe atual e superclasses
								for (Class<?> c = t.getClass(); !c.equals(EntidadeBaseTemId.class)
										&& !c.equals(EntidadeBase.class) && !c.equals(Object.class)
										&& c != null; c = c.getSuperclass()) {
									if (c.isAssignableFrom(subField.getType())) {
										final boolean subOrigem = subField.isAccessible();
										subField.setAccessible(true);
										CRUDBO.log.trace("OneToMany - vinculando atributo dependente ({})", subField);
										subField.set(linha, t);
										subField.setAccessible(subOrigem);
										break;
									}

								}
							}
						}
					}
					field.setAccessible(origem);
				} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	protected List<Field> getAllFields(Class<?> type) {
		List<Field> fields = new ArrayList<>();
		for (Class<?> c = type; !c.equals(EntidadeBaseTemId.class) && !c.equals(EntidadeBase.class)
				&& !c.equals(Object.class) && c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()).stream()
					.filter(f -> !Modifier.isStatic(f.getModifiers())).collect(Collectors.toList()));
		}
		return fields;
	}

	protected void vinculaOneToOne(final E t) {
		for (final Field field : getAllFields(t.getClass())) {
			if (EntidadeBase.class.isAssignableFrom(field.getType())) {
				try {
					final boolean origem = field.isAccessible();
					field.setAccessible(true);
					final EntidadeBase subVlr = (EntidadeBase) field.get(t);
					if (subVlr != null) {
						for (final Field subField : getAllFields(subVlr.getClass())) {
							for (Class<?> c = t.getClass(); !c.equals(EntidadeBaseTemId.class)
									&& !c.equals(EntidadeBase.class) && !c.equals(Object.class)
									&& c != null; c = c.getSuperclass()) {
								if (c.isAssignableFrom(subField.getType())) {
									final boolean subOrigem = subField.isAccessible();
									subField.setAccessible(true);
									CRUDBO.log.trace("OneToMany - vinculando atributo dependente ({})", subField);
									subField.set(subVlr, t);
									subField.setAccessible(subOrigem);
									break;
								}
							}
						}
					}
					field.setAccessible(origem);
				} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

}
