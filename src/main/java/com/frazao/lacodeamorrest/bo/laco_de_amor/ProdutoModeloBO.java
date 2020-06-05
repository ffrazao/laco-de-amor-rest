package com.frazao.lacodeamorrest.bo.laco_de_amor;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoAtributoDAO;
import com.frazao.lacodeamorrest.dao.laco_de_amor.ProdutoModeloDAO;
import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ImagemVendaDTO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.ProdutoModeloFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.ProdutoModelo;

@Service
public class ProdutoModeloBO
		extends CRUDBO<ProdutoModelo, java.lang.Integer, ProdutoModeloFiltroDTO, ProdutoModeloDAO> {

	public ProdutoModeloBO(@Autowired final ProdutoModeloDAO dao) {
		super(ProdutoModelo.class, dao);
	}

	@Autowired
	private ProdutoAtributoDAO produtoAtributoDAO;

	@Override
	public ProdutoModelo entrando(@Valid ProdutoModelo t, @Valid ProdutoModelo anterior, String acao)
			throws BOException {
		if (ObjectUtils.allNotNull(t, t.getProdutoDescricaoList())) {
			t.getProdutoDescricaoList()
					.forEach(p -> p.setProdutoAtributo(
							ObjectUtils.allNotNull(p.getProdutoAtributo(), p.getProdutoAtributo().getId())
									? produtoAtributoDAO.findById(p.getProdutoAtributo().getId()).get()
									: p.getProdutoAtributo()));
		}
		return super.entrando(t, anterior, acao);
	}

	public List<ImagemVendaDTO> getImagemVenda() {
		List<ImagemVendaDTO> result;
		List<ProdutoModelo> produtoModeloList = this.getDAO().findAllByMateriaPrimaAndFotoIsNotNull(Confirmacao.N);
		result = produtoModeloList.stream()
				.map(pm -> new ImagemVendaDTO(pm.getId(), pm.getNome(), pm.getCodigo(), pm.getFoto()))
				.collect(Collectors.toList());
		return result;
	}

}
