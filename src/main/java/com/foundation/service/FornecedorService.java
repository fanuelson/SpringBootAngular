package com.foundation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.FornecedorDAO;
import com.foundation.model.Composicao;
import com.foundation.model.Fornecedor;
import com.foundation.validador.ValidadorFornecedor;

@Service
@RequestScope
public class FornecedorService {

	@Autowired
	private FornecedorDAO fornecedorDAO;
	
	@Autowired
	private ValidadorFornecedor validadorFornecedor;

	public Fornecedor save(Fornecedor fornecedor) {
		validadorFornecedor.validarSalvar(fornecedor);
		return fornecedorDAO.save(fornecedor);
	}
	
	public void save(List<Composicao> composicoes) {
	}
	
}