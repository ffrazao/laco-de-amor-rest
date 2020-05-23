package com.frazao.lacodeamorrest.bo;

import java.lang.reflect.Field;
import java.util.Collection;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.frazao.lacodeamorrest.dao.Filtro;
import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBase;
import com.frazao.lacodeamorrest.modelo.entidade.TemId;

public abstract class CRUDBO<T, Id, F extends FiltroDTO> implements BO {

	private static final Logger LOG = LoggerFactory.getLogger(CRUDBO.class);

	private final Class<T> clazz;

	private final JpaRepository<T, Id> dao;

	public CRUDBO(final Class<T> clazz, final JpaRepository<T, Id> dao) {
		if (CRUDBO.LOG.isDebugEnabled()) {
			CRUDBO.LOG.debug("Novo BO ({})", clazz);
		}
		this.clazz = clazz;
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Id create(final T t) {
		if (CRUDBO.LOG.isDebugEnabled()) {
			CRUDBO.LOG.debug("Criando ({})..", t);
		}

		// adicionar link reverso
		vinculaOneToMany(t);
		vinculaOneToOne(t);

		this.getDAO().save(t);
		if (CRUDBO.LOG.isTraceEnabled()) {
			CRUDBO.LOG.trace("Criado ({})..", t);
		}
		return ((TemId<Id>) t).getId();
	}

	@Transactional
	public void delete(final Id id) {
		if (CRUDBO.LOG.isDebugEnabled()) {
			CRUDBO.LOG.debug("Excluindo ({})..", id);
		}
		this.getDAO().deleteById(id);
		if (CRUDBO.LOG.isTraceEnabled()) {
			CRUDBO.LOG.trace("Excluido ({})..", id);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<T> filter(final F filtro) {
		if (CRUDBO.LOG.isDebugEnabled()) {
			CRUDBO.LOG.debug("Filtrando ({})..", filtro);
		}
		final Collection<T> result = ((Filtro<T, F>) this.getDAO()).filtrar(filtro);
		if (CRUDBO.LOG.isTraceEnabled()) {
			CRUDBO.LOG.trace("Filtrado ({})..", result);
		}
		return result;
	}

	public JpaRepository<T, Id> getDAO() {
		return this.dao;
	}

	public T instantiate() {
		try {
			if (CRUDBO.LOG.isTraceEnabled()) {
				CRUDBO.LOG.trace("Nova instancia ({})..", this.clazz);
			}
			return this.clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public T novo(final T modelo) {
		if (CRUDBO.LOG.isDebugEnabled()) {
			CRUDBO.LOG.debug("Criando novo ({})..", modelo);
		}
		final T result = modelo == null ? this.instantiate() : modelo;
		if (CRUDBO.LOG.isTraceEnabled()) {
			CRUDBO.LOG.trace("Criado novo ({})..", result);
		}
		return result;
	}

	public T restore(final Id id) {
		if (CRUDBO.LOG.isDebugEnabled()) {
			CRUDBO.LOG.debug("Recuperando ({})..", id);
		}
		final T result = this.getDAO().getOne(id);
		if (CRUDBO.LOG.isDebugEnabled()) {
			CRUDBO.LOG.debug("Recuperado ({})..", result);
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public T update(final Id id, final T t) {
		if (CRUDBO.LOG.isDebugEnabled()) {
			CRUDBO.LOG.debug("Salvando ({})..", id);
		}
		((TemId<Id>) t).setId(id);

		// adicionar link reverso
		vinculaOneToMany(t);
		vinculaOneToOne(t);

		final T result = this.getDAO().save(t);

		if (CRUDBO.LOG.isTraceEnabled()) {
			CRUDBO.LOG.trace("Salvo ({})..", result);
		}
		return result;
	}

	protected void vinculaOneToMany(T t) {
		for (Field field : t.getClass().getDeclaredFields()) {
			if (Collection.class.isAssignableFrom(field.getType())) {
				try {
					boolean origem = field.isAccessible();
					field.setAccessible(true);
					if (field.get(t) != null) {
						for (Object linha : (Collection<?>) field.get(t)) {
							for (Field subField : linha.getClass().getDeclaredFields()) {
								if (t.getClass().isAssignableFrom(subField.getType())) {
									boolean subOrigem = subField.isAccessible();
									subField.setAccessible(true);
									LOG.trace("OneToMany - vinculando atributo dependente ({})", subField);
									subField.set(linha, t);
									subField.setAccessible(subOrigem);
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

	protected void vinculaOneToOne(T t) {
		for (Field field : t.getClass().getDeclaredFields()) {
			if (EntidadeBase.class.isAssignableFrom(field.getType())) {
				try {
					boolean origem = field.isAccessible();
					field.setAccessible(true);
					EntidadeBase subVlr = (EntidadeBase) field.get(t);
					if (subVlr != null) {
						for (Field subField : subVlr.getClass().getDeclaredFields()) {
							if (t.getClass().isAssignableFrom(subField.getType())) {
								boolean subOrigem = subField.isAccessible();
								subField.setAccessible(true);
								LOG.trace("OneToMany - vinculando atributo dependente ({})", subField);
								subField.set(subVlr, t);
								subField.setAccessible(subOrigem);
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
