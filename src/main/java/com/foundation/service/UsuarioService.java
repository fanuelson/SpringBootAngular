package com.foundation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.dao.UsuarioDAO;
import com.foundation.model.Usuario;
import com.foundation.utils.CollectionUtils;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	public List<Usuario> findAll() {
		return CollectionUtils.toList(usuarioDAO.findAll());
	}
	
	public Usuario findByLogin(String login) {
		return usuarioDAO.findByLogin(login);
	}
}
