package com.frazao.lacodeamorrest.bo;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frazao.lacodeamorrest.dao.Filtro;
import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.TemId;

public class CRUDBO<T, Id, F extends FiltroDTO> implements BO {

	private JpaRepository<T, Id> dao;

	public CRUDBO(JpaRepository<T, Id> dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Id create(T t) {
		this.getDAO().save(t);
		return (Id) ((TemId<Id>) t).getId();
	}

	@Transactional
	public void delete(Id id) {
		this.getDAO().deleteById(id);
	}

	@SuppressWarnings("unchecked")
	public Collection<T> filter(F filtro) {
		Collection<T> result = ((Filtro<T, F>) this.getDAO()).filtrar(filtro);
		return result;
	}

	public JpaRepository<T, Id> getDAO() {
		return this.dao;
	}

	public T restore(Id id) {
		Optional<T> result = this.getDAO().findById(id);
		return result.get();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public T update(Id id, T t) {
		T salvo = (T) this.restore(id);
		((TemId<Id>) t).setId(id);
		T result = (T) this.getDAO().save(t);
		return result;
	}

}
