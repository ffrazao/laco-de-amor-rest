package com.frazao.lacodeamorrest.rest.laco_de_amor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frazao.lacodeamorrest.bo.laco_de_amor.ProdutoModeloBO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ImagemVendaDTO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoModeloFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoModelo;
import com.frazao.lacodeamorrest.rest.CRUDREST;

@RestController
@RequestMapping(value = "produto-modelo")
public class ProdutoModeloCRUDREST
		extends CRUDREST<ProdutoModelo, java.lang.Integer, ProdutoModeloFiltroDTO, ProdutoModeloBO> {

	public ProdutoModeloCRUDREST(@Autowired final ProdutoModeloBO bo) {
		super(bo);
	}

	@GetMapping("imagem-venda")
	public List<ImagemVendaDTO> getImagemVenda() {
		return this.getBO().getImagemVenda();
	}

}
