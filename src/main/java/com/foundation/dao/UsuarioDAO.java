package com.foundation.dao;

import org.springframework.data.repository.CrudRepository;

import com.foundation.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Long> {

	Usuario findByLogin(String login);
}
