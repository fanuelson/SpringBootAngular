package com.foundation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.ComposicaoDAO;
import com.foundation.model.Composicao;
import com.foundation.utils.CustomCollectionUtils;
import com.foundation.validador.ValidadorComposicao;

@Service
@RequestScope
public class ComposicaoService extends AbstractService {

	@Autowired
	private ComposicaoDAO composicaoDAO;
	
	@Autowired
	private ValidadorComposicao validadorComposicao;
	
	public List<Composicao> save(List<Composicao> composicoes) {
		limparValidacoes();
		validadorComposicao.validarSalvar(composicoes, this);
		assertValid();
		return CustomCollectionUtils.toList(composicaoDAO.save(composicoes));
	}
	
	public void removeAll(List<Composicao> composicoes) {
		composicaoDAO.delete(composicoes);
	}
	
	public List<Composicao> findAllByInsumo(Long idInsumo) {
		return composicaoDAO.findAllByInsumo(idInsumo);
	}
	
}