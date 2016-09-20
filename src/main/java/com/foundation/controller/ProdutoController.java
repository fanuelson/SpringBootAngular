package com.foundation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foundation.model.Produto;
import com.foundation.service.ProdutoService;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> findAll() {
		return produtoService.findAll();
	}
	
	@RequestMapping(path = "/page", method = RequestMethod.GET)
	public Page<Produto> findAll(Pageable page) {
		return produtoService.findAll(page);
	}
}
