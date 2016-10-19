package com.foundation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.FornecedorDAO;
import com.foundation.filtroConsulta.FiltroConsultaFornecedor;
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
		validadorFornecedor.validarSalvar(fornecedor, this);
		return fornecedorDAO.save(fornecedor);
	}
	
	public Fornecedor findByCpfCnpj(Long cpfCnpj) {
		return fornecedorDAO.findByCpfCnpj(cpfCnpj);
	}
	
	public Page<Fornecedor> findByFiltro(FiltroConsultaFornecedor filro, Pageable page) {
		return fornecedorDAO.findByFilter(filro, page);
	}
	
	public boolean existsFornecedorWithCpfCnpj(Long cpfCnpj) {
		return findByCpfCnpj(cpfCnpj) != null;
	}
	
	public boolean existsFornecedorWithCpfCnpj(String cpfCnpj) {
		return findByCpfCnpj(new Long(cpfCnpj)) != null;
	}
	
}