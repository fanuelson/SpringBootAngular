package com.foundation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foundation.model.Pessoa;
import com.foundation.service.PessoaService;

@RestController
@RequestMapping("pessoas")
public class PessoaController {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Pessoa> getAll() {
		PessoaService ps = new PessoaService();
		return ps.getAll();
	}
}
