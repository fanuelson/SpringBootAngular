package com.foundation.service;

import java.util.ArrayList;
import java.util.List;

import com.foundation.model.Pessoa;

public class PessoaService {

	public List<Pessoa> getAll() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas.add(new Pessoa("Pess1", 3));
		pessoas.add(new Pessoa("Pess3", 5));
		pessoas.add(new Pessoa("Pess2", 4));
		
		return pessoas;
	}
}
