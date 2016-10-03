package com.foundation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dto.BasicResponseDTO;
import com.foundation.exception.ValidacaoException;
import com.foundation.model.Produto;
import com.foundation.service.ProdutoService;
import com.foundation.validacao.Validacoes;

@RestController
@RequestMapping("produtos")
@RequestScope
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(path = "/page", method = RequestMethod.GET)
	public Page<Produto> findAll(Pageable page) {
		return produtoService.findAll(page);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Produto produto) {
		try{
			BasicResponseDTO basicResponse = new BasicResponseDTO(produtoService.save(produto), "Registro Salvo com sucesso!");
			return new ResponseEntity<BasicResponseDTO>(basicResponse, HttpStatus.CREATED);
		} catch (ValidacaoException e) {
			return new ResponseEntity<Validacoes>(e.getValidacoes(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		produtoService.delete(id);
	}
}
