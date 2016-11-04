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
import com.foundation.filtroConsulta.FiltroConsultaFornecedor;
import com.foundation.model.Fornecedor;
import com.foundation.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedores")
@RequestScope
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Fornecedor fornecedor) {
		BasicResponseDTO basicResponse = new BasicResponseDTO(fornecedorService.save(fornecedor), "Registro Salvo com sucesso.");
		return ResponseEntity.status(HttpStatus.CREATED).body(basicResponse);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		fornecedorService.delete(id);
		BasicResponseDTO basicResponse = new BasicResponseDTO("Registro Removido com sucesso.");
		return ResponseEntity.status(HttpStatus.OK).body(basicResponse);
	}
	
	@GetMapping(path = "/page")
	public Page<Fornecedor> findAll(Pageable page) {
		return fornecedorService.findAll(page);
	}
	
	@PostMapping(path = "/page/filterBy")
	public Page<Fornecedor> findByFiltro(@RequestBody FiltroConsultaFornecedor filtro, Pageable page) {
		return fornecedorService.findByFilter(filtro, page);
	}
	
}
