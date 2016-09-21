package com.foundation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foundation.dao.ProdutoDAO;
import com.foundation.model.Produto;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoDAO produtoDAO;

	public Page<Produto> findAll(Pageable page) {
		return produtoDAO.findAll(page);
	}
}