package com.foundation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dto.BasicResponseDTO;
import com.foundation.model.Composicao;
import com.foundation.service.ComposicaoService;

@RestController
@RequestMapping("/api/composicoes")
@RequestScope
public class ComposicaoController {

	@Autowired
	private ComposicaoService composicaoService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody List<Composicao> composicoes) {
		BasicResponseDTO basicResponse = new BasicResponseDTO(composicaoService.save(composicoes), "Registros Salvos com sucesso.");
		return ResponseEntity.status(HttpStatus.CREATED).body(basicResponse);
	}
	
}
