package com.frazao.lacodeamorrest.bo.laco_de_amor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.VenderDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.VenderFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Vender;

@Service
public class VenderBO extends CRUDBO<Vender, Integer, VenderFiltroDTO> {

	public VenderBO(@Autowired final VenderDAO dao) {
		super(Vender.class, dao);
	}

	@Autowired
	private EventoBO eventoBO;

	@Override
	public Vender novo(Vender modelo) {
		Vender result = (Vender) this.eventoBO.novo(super.novo(modelo));
		return result;
	}

}
