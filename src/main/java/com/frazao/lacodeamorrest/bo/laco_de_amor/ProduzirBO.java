package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProduzirDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProduzirFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Produzir;

@Service
public class ProduzirBO extends CRUDBO<Produzir, Integer, ProduzirFiltroDTO> {

	public ProduzirBO(@Autowired final ProduzirDAO dao) {
		super(Produzir.class, dao);
	}

	@Autowired
	private EventoBO eventoBO;

	@Override
	public Produzir novo(Produzir modelo) {
		Produzir result = (Produzir) this.eventoBO.novo(super.novo(modelo));
		return result;
	}

}
