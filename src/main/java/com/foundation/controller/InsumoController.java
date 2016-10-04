package com.foundation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dto.BasicResponseDTO;
import com.foundation.exception.ValidacaoException;
import com.foundation.model.Insumo;
import com.foundation.service.InsumoService;
import com.foundation.validacao.Validacoes;

@RestController
@RequestMapping("insumos")
@RequestScope
public class InsumoController {

	@Autowired
	private InsumoService insumoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Insumo> findAll() {
		return insumoService.findAll();
	}

	@RequestMapping(path = "/page", method = RequestMethod.GET)
	public Page<Insumo> findAll(Pageable page) {
		return insumoService.findAll(page);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Insumo insumo) {
		try {
			BasicResponseDTO basicResponse = new BasicResponseDTO(insumoService.save(insumo), "Registro Salvo com sucesso.");
			return ResponseEntity.status(HttpStatus.CREATED).body(basicResponse);
		} catch (ValidacaoException e) {
			return new ResponseEntity<Validacoes>(e.getValidacoes(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
