package com.frazao.lacodeamorrest.bo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;

public class CRUDBO<T, Id, F extends FiltroDTO> implements BO {

	private JpaRepository<T, Id> dao;

	public CRUDBO(JpaRepository<T, Id> dao) {
		this.dao = dao;
	}

	@Transactional
	public Id create(T t) {
		this.getDAO().save(t);
		return null;
	}

	@Transactional
	public void delete(Id id) {
		T result = this.restore(id);
		this.getDAO().delete(result);
	}

	public List<T> filter(F filtro) {
		List<T> result = this.getDAO().findAll();
		return result;
	}
	
	public JpaRepository<T, Id> getDAO() {
		return this.dao;
	}

	public T restore(Id id) {
		Optional<T> result = this.getDAO().findById(id);
		return result.get();
	}

	@Transactional
	public T update(Id id, T t) {
		T result = (T) this.getDAO().save(t);
		return result;
	}

}
