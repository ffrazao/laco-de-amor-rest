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

	private final BO bo;

	public CRUDREST(final BO bo) {
		this.bo = bo;
	}

	@PostMapping
	public Id create(@RequestBody final T t) throws Exception {
		final Id result = this.getBO().create(t);
		return result;
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") final Id id) throws Exception {
		this.getBO().delete(id);
	}

	@GetMapping
	public Collection<T> filter(final F filtro) throws Exception {
		final Collection<T> result = this.getBO().filter(filtro);
		return result;
	}

	public BO getBO() {
		BO result = this.bo;
		return result;
	}

	@PostMapping("novo")
	public T novo(@RequestBody(required = false) final T modelo) {
		final T result = this.getBO().novo(modelo);
		return result;
	}

	@GetMapping("{id}")
	public T restore(@PathVariable("id") final Id id) throws Exception {
		final T result = this.getBO().restore(id);
		return result;
	}

	@PutMapping("{id}")
	public void update(@PathVariable("id") final Id id, @RequestBody final T t) throws Exception {
		this.getBO().update(id, t);
	}

}
