package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ParceiroDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ParceiroFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Parceiro;

@Service
public class ParceiroBO extends CRUDBO<Parceiro, Integer, ParceiroFiltroDTO, ParceiroDAO> {

	public ParceiroBO(@Autowired final ParceiroDAO dao) {
		super(Parceiro.class, dao);
	}

}
