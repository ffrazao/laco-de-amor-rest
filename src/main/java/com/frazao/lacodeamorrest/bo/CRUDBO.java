package com.frazao.lacodeamorrest.bo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frazao.lacodeamorrest.dao.Filtro;
import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBase;
import com.frazao.lacodeamorrest.modelo.entidade.EntidadeBaseTemId;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public abstract class CRUDBO<E extends EntidadeBaseTemId<Id>, Id, F extends FiltroDTO, D extends JpaRepository<E, Id>>
		implements BO, CRUD<E, Id, F, D> {

	private final D dao;

	private final Class<E> entidadeClasse;

	public CRUDBO(final Class<E> clazz, final D dao) {
		if (CRUDBO.log.isDebugEnabled()) {
			CRUDBO.log.debug("Novo BO ({})", clazz);
		}
		this.entidadeClasse = clazz;
		this.dao = dao;
	}

	@Override
	@Transactional
	public Id create(@Valid final E t) throws BOException {
		try {
			if (CRUDBO.log.isDebugEnabled()) {
				CRUDBO.log.debug("Criando ({})..", t);
			}
			final E entidade = this.getDAO().save(this.entrando(t, null, "C"));
			t.setId(entidade.getId());
			if (CRUDBO.log.isTraceEnabled()) {
				CRUDBO.log.trace("Criado ({})..", t);
			}
			this.entrou(t, "C");
			return t.getId();
		} catch (final Exception e) {
			throw new BOException("Erro ao criar registro", e);
		}

	}

	public void delete(final Collection<Id> ids) throws BOException {
		for (final Id id : ids) {
			this.delete(id);
		}
	}

	public void delete(final E e) throws BOException {
		this.delete(e.getId());
	}

	@Override
	@Transactional
	public void delete(final Id id) throws BOException {
		try {
			final E anterior = this.getDAO().getOne(id);
			if (CRUDBO.log.isTraceEnabled()) {
				CRUDBO.log.trace("Excluindo dependencias ({})..", id);
			}
			this.excluindo(anterior, id);

			if (CRUDBO.log.isDebugEnabled()) {
				CRUDBO.log.debug("Excluindo ({})..", id);
			}
			this.getDAO().deleteById(id);

			if (CRUDBO.log.isTraceEnabled()) {
				CRUDBO.log.trace("Excluido ({})..", id);
			}
			this.excluido(anterior, id);
		} catch (final Exception e) {
			throw new BOException("Erro ao excluir registro", e);
		}
	}

	public void delete(final Id[] ids) throws BOException {
		this.delete(Arrays.asList(ids));
	}

	public E entrando(@Valid final E t, @Valid final E anterior, final String acao) throws BOException {
		try {
			// adicionar link reverso
			this.vinculaOneToMany(t);
			this.vinculaOneToOne(t);
			return t;
		} catch (final Exception e) {
			throw new BOException("Erro ao vincular dependencias do registro", e);
		}
	}

	public E entrou(@Valid final E t, final String acao) throws BOException {
		final E result = t;
		return result;
	}

	public void excluido(final E anterior, final Id id) throws BOException {

	}

	public void excluindo(final E anterior, final Id id) throws BOException {

	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<E> filter(@Valid final F filtro) throws BOException {
		try {
			if (CRUDBO.log.isDebugEnabled()) {
				CRUDBO.log.debug("Filtrando ({})..", filtro);
			}
			final Collection<E> result = ((Filtro<E, F>) this.getDAO()).filtrar(this.saindo(filtro, "F"));
			if (CRUDBO.log.isTraceEnabled()) {
				CRUDBO.log.trace("Filtrado ({})..", result);
			}
			return this.saiu(result, "F");
		} catch (final Exception e) {
			throw new BOException("Erro ao buscar registros", e);
		}
	}

	protected List<Field> getAllFields(final Class<?> type) {
		final List<Field> fields = new ArrayList<>();
		for (Class<?> c = type; !c.equals(EntidadeBaseTemId.class) && !c.equals(EntidadeBase.class)
				&& !c.equals(Object.class) && c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()).stream()
					.filter(f -> !Modifier.isStatic(f.getModifiers())).collect(Collectors.toList()));
		}
		return fields;
	}

	@Override
	public D getDAO() {
		return this.dao;
	}

	protected <En extends EntidadeBaseTemId<Iden>, Iden> Collection<Iden> idList(final Collection<En> t) {
		return t.stream().filter(e -> e.getId() != null).map((e) -> e.getId()).collect(Collectors.toList());
	}

	protected <En extends EntidadeBaseTemId<Iden>, Iden> Collection<Iden> idsOrfaos(final Collection<En> anterior,
			final Collection<En> t) {
		Collection<Iden> result = new ArrayList<>();
		if (anterior != null && anterior.size() > 0) {
			final Collection<Iden> ids = this.idList(t);
			result = this.idList(anterior).stream().filter(e -> !ids.contains(e)).collect(Collectors.toList());
		}
		return result;
	}

	public E instantiate() throws BOException {
		try {
			if (CRUDBO.log.isTraceEnabled()) {
				CRUDBO.log.trace("Nova instancia ({})..", this.entidadeClasse);
			}
			return this.entidadeClasse.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BOException(e);
		}
	}

	@Override
	public E novo(@Valid final E modelo) throws BOException {
		try {
			if (CRUDBO.log.isDebugEnabled()) {
				CRUDBO.log.debug("Criando novo ({})..", modelo);
			}
			final E result = modelo == null ? this.instantiate() : modelo;
			if (CRUDBO.log.isTraceEnabled()) {
				CRUDBO.log.trace("Criado novo ({})..", result);
			}
			return result;
		} catch (final Exception e) {
			throw new BOException("Erro ao preparar novo registro", e);
		}
	}

	@Override
	public E restore(final Id id) throws BOException {
		try {
			if (CRUDBO.log.isDebugEnabled()) {
				CRUDBO.log.debug("Recuperando ({})..", id);
			}
			final E result = this.getDAO().getOne(this.saindo(id, "R"));
			if (CRUDBO.log.isDebugEnabled()) {
				CRUDBO.log.debug("Recuperado ({})..", result);
			}
			return this.saiu(result, "R");
		} catch (final Exception e) {
			throw new BOException("Erro ao buscar registro", e);
		}
	}

	public F saindo(@Valid final F filtro, final String acao) throws BOException {
		final F result = filtro;
		return result;
	}

	public Id saindo(@Valid final Id id, final String acao) throws BOException {
		final Id result = id;
		return result;
	}

	public Collection<E> saiu(@Valid final Collection<E> t, final String acao) throws BOException {
		final List<E> result = new ArrayList<>();
		for (int i = 0; i < t.size(); i++) {
			result.add(this.saiu(((List<E>) t).get(i), acao));
		}
		return result;
	}

	public E saiu(@Valid final E t, final String acao) throws BOException {
		final E result = t;
		return result;
	}

	@Override
	@Transactional
	public E update(final Id id, @Valid final E t) throws BOException {
		try {
			if (CRUDBO.log.isDebugEnabled()) {
				CRUDBO.log.debug("Atualizando ({})..", id);
			}
			final E anterior = this.getDAO().getOne(t.getId());

			t.setId(id);
			this.getDAO().save(this.entrando(t, anterior, "U"));

			if (CRUDBO.log.isTraceEnabled()) {
				CRUDBO.log.trace("Atualizado ({})..", t);
			}
			return this.entrou(t, "U");
		} catch (final Exception e) {
			throw new BOException("Erro ao atualizar registro", e);
		}
	}

	protected void vinculaOneToMany(final E t) {
		for (final Field field : this.getAllFields(t.getClass())) {
			if (Collection.class.isAssignableFrom(field.getType())) {
				try {
					final boolean origem = field.isAccessible();
					field.setAccessible(true);
					if (field.get(t) != null) {
						for (final Object linha : (Collection<?>) field.get(t)) {
							for (final Field subField : this.getAllFields(linha.getClass())) {
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

	protected void vinculaOneToOne(final E t) {
		for (final Field field : this.getAllFields(t.getClass())) {
			if (EntidadeBase.class.isAssignableFrom(field.getType())) {
				try {
					final boolean origem = field.isAccessible();
					field.setAccessible(true);
					final EntidadeBase subVlr = (EntidadeBase) field.get(t);
					if (subVlr != null) {
						for (final Field subField : this.getAllFields(subVlr.getClass())) {
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
