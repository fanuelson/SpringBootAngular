package com.foundation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foundation.model.Usuario;

@Service
public class UsuarioService {

	public List<Usuario> findAll() {
		List<Usuario> pessoas = new ArrayList<Usuario>();
		pessoas.add(new Usuario("Fanu"));
		pessoas.add(new Usuario("Joao"));
		pessoas.add(new Usuario("José"));
		return pessoas;
	}
}
