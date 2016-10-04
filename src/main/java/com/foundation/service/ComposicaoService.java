package com.foundation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.ComposicaoDAO;
import com.foundation.model.Composicao;
import com.foundation.validador.ValidadorComposicao;

@Service
@RequestScope
public class ComposicaoService {

	@Autowired
	private ComposicaoDAO composicaoDAO;
	
	@Autowired
	private ValidadorComposicao validacoesComposicao;
	
	public void save(List<Composicao> composicoes) {
		validacoesComposicao.validarSalvar(composicoes);
		composicaoDAO.save(composicoes);
	}
	
}