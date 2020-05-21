package com.frazao.lacodeamorrest.rest;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.modelo.dto.FiltroDTO;

@RestController
public abstract class CRUDREST<T, Id, F extends FiltroDTO, BO extends CRUDBO<T, Id, F>> {

	private BO bo;

	public CRUDREST(BO bo) {
		this.bo = bo;
	}

	@PostMapping
	public Id create(@RequestBody T t) throws Exception {
		Id result = this.getBO().create(t);
		return result;
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Id id) throws Exception {
		this.getBO().delete(id);
	}

	@GetMapping
	public Collection<T> filter(F filtro) throws Exception {
		Collection<T> result = this.getBO().filter(filtro);
		return result;
	}

	public BO getBO() {
		return this.bo;
	}

	@PostMapping("novo")
	public abstract T novo(@RequestBody T modelo) throws Exception;

	@GetMapping("{id}")
	public T restore(@PathVariable("id") Id id) throws Exception {
		T result = (T) this.getBO().restore(id);
		return result;
	}

	@PutMapping("{id}")
	public void update(@PathVariable("id") Id id, @RequestBody T t) throws Exception {
		this.getBO().update(id, t);
	}

}
