package com.frazao.lacodeamorrest.bo.laco_de_amor;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoDAO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.EventoTipoDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.EventoFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Evento;

@Service
public class EventoBO extends CRUDBO<Evento, java.lang.Integer, EventoFiltroDTO> {

	public EventoBO(@Autowired final EventoDAO dao) {
		super(Evento.class, dao);
	}

	@Autowired
	private EventoTipoDAO eventoTipoDAO;

	@Override
	public Evento novo(Evento modelo) {
		Evento result = super.novo(modelo);
		try {
			result.setEventoTipo(this.eventoTipoDAO.getFindByCodigo((String) modelo.getClass().getField("CODIGO").get(modelo)));
		} catch (Exception e) {
			System.exit(0);
		}
		result.setData(LocalDateTime.now());
		return result;
	}

}
