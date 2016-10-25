package com.foundation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.ComposicaoDAO;
import com.foundation.model.Composicao;
import com.foundation.utils.CollectionUtils;
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
		validadorComposicao.validarSalvar(composicoes, getValidacoes());
		assertValid();
		return CollectionUtils.toList(composicaoDAO.save(composicoes));
	}
	
}