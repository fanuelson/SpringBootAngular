package com.foundation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dto.BasicResponseDTO;
import com.foundation.model.Produto;
import com.foundation.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
@RequestScope
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		BasicResponseDTO basicResponse = new BasicResponseDTO(produtoService.findOne(id));
		return ResponseEntity.ok(basicResponse);
	}
	
	@GetMapping(path = "/page")
	public Page<Produto> findAll(Pageable page) {
		return produtoService.findAll(page);
	}
	
	@GetMapping(path = "/{id}/toggleStatus")
	public ResponseEntity<?> toggleStatus(@PathVariable Long id) {
		BasicResponseDTO basicResponse = new BasicResponseDTO(produtoService.toggleStatus(id), "Registro Salvo com sucesso!");
		return ResponseEntity.ok(basicResponse);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Produto produto) {
		BasicResponseDTO basicResponse = new BasicResponseDTO(produtoService.save(produto), "Registro Salvo com sucesso!");
		return ResponseEntity.status(HttpStatus.CREATED).body(basicResponse);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		produtoService.delete(id);
		BasicResponseDTO basicResponse = new BasicResponseDTO("Registro Removido com sucesso.");
		return ResponseEntity.status(HttpStatus.OK).body(basicResponse);
	}
}
