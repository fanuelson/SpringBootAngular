package com.foundation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foundation.model.Usuario;
import com.foundation.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> findAll() {
		return usuarioService.findAll();
	}
	
	@GetMapping(path = "/{login}")
	public Usuario findByLogin(@PathVariable String login) {
		return usuarioService.findByLogin(login);
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public Usuario save(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}
}
