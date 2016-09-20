package com.foundation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foundation.dao.ProdutoDAO;
import com.foundation.model.Produto;
import com.foundation.utils.CollectionUtils;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoDAO produtoDAO;

	public List<Produto> findAll() {
		return CollectionUtils.toList(produtoDAO.findAll());
	}
	
	public Page<Produto> findAll(Pageable page) {
		return produtoDAO.findAll(page);
	}
}