package com.foundation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.UsuarioDAO;
import com.foundation.model.Usuario;
import com.foundation.utils.CollectionUtils;
import com.foundation.validador.ValidadorUsuario;

@Service
@RequestScope
public class UsuarioService extends AbstractService{
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ValidadorUsuario validadorUsuario;

	public List<Usuario> findAll() {
		return CollectionUtils.toList(usuarioDAO.findAll());
	}
	
	public Usuario findByLogin(String login) {
		return usuarioDAO.findByLogin(login);
	}
	
	public Usuario save(Usuario usuario) {
		limparValidacoes();
		validadorUsuario.validarSalvar(usuario, this);
		assertValid();
		return usuarioDAO.save(usuario);
	}
}
