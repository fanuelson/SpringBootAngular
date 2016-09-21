package com.foundation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.foundation.dao.ProdutoDAO;
import com.foundation.model.Produto;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoDAO produtoDAO;

	public Page<Produto> findAll(Pageable page) {
		return produtoDAO.findAll(page);
	}

	public Produto save(Produto produto) {
		validate(produto);
		return produtoDAO.save(produto);
	}

	public void delete(Long id) {
		produtoDAO.delete(id);
	}

	private void validate(Produto produto) {
		if (produto.getNome() == null || StringUtils.isEmpty(produto.getNome().trim())) {
			throw new RuntimeException("Campo Nome Obrigatório.");
		}
	}
}