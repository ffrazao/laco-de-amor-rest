package com.frazao.lacodeamorrest.bo;

import java.util.Collection;

public interface CRUD<E, Id, F, D> {

	Id create(E t) throws BOException;

	void delete(Id id) throws BOException;

	Collection<E> filter(F filtro) throws BOException;

	D getDAO();

	Class<E> getEntidadeClasse();

	E novo(E modelo) throws BOException;

	E restore(Id id) throws BOException;

	E update(Id id, E t) throws BOException;

}