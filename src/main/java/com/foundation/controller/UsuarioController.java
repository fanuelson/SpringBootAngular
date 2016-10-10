package com.foundation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foundation.model.Usuario;
import com.foundation.service.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> findAll() {
		return usuarioService.findAll();
	}
	
	@RequestMapping(path = "/{login}", method = RequestMethod.GET)
	public Usuario findByLogin(@PathVariable String login) {
		return usuarioService.findByLogin(login);
	}
}
