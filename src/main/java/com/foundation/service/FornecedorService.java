package com.foundation.service;

import org.apache.commons.lang3.StringUtils;
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
public class FornecedorService extends AbstractService {

	@Autowired
	private ValidadorFornecedor validadorFornecedor;

	@Autowired
	private FornecedorDAO fornecedorDAO;

	public Fornecedor save(Fornecedor fornecedor) {
		limparValidacoes();
		validadorFornecedor.validarSalvar(fornecedor, this);
		assertValid();
		ajustarCpfCnpj(fornecedor);
		return fornecedorDAO.save(fornecedor);
	}
	
	private void ajustarCpfCnpj(Fornecedor fornecedor) {
		String cpfCnpj = fornecedor.getCpfCnpj();
		fornecedor.setCpfCnpj(StringUtils.trimToNull(cpfCnpj));
	}

	public void delete(Long idForn) {
		fornecedorDAO.delete(idForn);
	}
	
	public Fornecedor findByCpfCnpj(String cpfCnpj) {
		return fornecedorDAO.findByCpfCnpj(cpfCnpj);
	}
	
	public Page<Fornecedor> findByFilter(FiltroConsultaFornecedor filtro, Pageable page) {
		return fornecedorDAO.findByFilter(filtro, page);
	}
	
	public boolean existsFornecedorWithCpfCnpj(String cpfCnpj) {
		return findByCpfCnpj(cpfCnpj) != null;
	}

	public Page<Fornecedor> findAll(Pageable page) {
		return fornecedorDAO.findAll(page);
	}
	
}