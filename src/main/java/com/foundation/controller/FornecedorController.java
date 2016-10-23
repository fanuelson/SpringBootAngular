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
import com.foundation.filtroConsulta.FiltroConsultaFornecedor;
import com.foundation.model.Fornecedor;
import com.foundation.service.FornecedorService;
import com.foundation.validacao.Validacoes;

@RestController
@RequestMapping("/api/fornecedores")
@RequestScope
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Fornecedor fornecedor) {
		try {
			BasicResponseDTO basicResponse = new BasicResponseDTO(fornecedorService.save(fornecedor), "Registro Salvo com sucesso.");
			return ResponseEntity.status(HttpStatus.CREATED).body(basicResponse);
		} catch (ValidacaoException e) {
			return new ResponseEntity<Validacoes>(e.getValidacoes(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		fornecedorService.delete(id);
		BasicResponseDTO basicResponse = new BasicResponseDTO("Registro Removido com sucesso.");
		return ResponseEntity.status(HttpStatus.OK).body(basicResponse);
	}
	
	@RequestMapping(path = "/page", method = RequestMethod.GET)
	public Page<Fornecedor> findAll(Pageable page) {
		return fornecedorService.findAll(page);
	}
	
	@RequestMapping(path = "/page/filterBy", method = RequestMethod.POST)
	public Page<Fornecedor> findByFiltro(@RequestBody FiltroConsultaFornecedor filtro, Pageable page) {
		return fornecedorService.findByFilter(filtro, page);
	}
	
}
