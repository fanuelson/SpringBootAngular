package com.foundation.controller;

import java.util.Arrays;

import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dto.BasicResponseDTO;
import com.foundation.model.Usuario;
import com.foundation.service.TokenService;
import com.foundation.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@RequestScope
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody final UserLogin userLogin) throws ServletException {
		if(userLogin == null) {
			return null;
		}
		if (StringUtils.isAnyBlank(userLogin.login, userLogin.senha)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BasicResponseDTO("Invalid login"));
		}
		Usuario user = usuarioService.findByLogin(userLogin.login);
		if(user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BasicResponseDTO("Invalid login"));
		}
		
		String token = tokenService.generateToken(user.getId(), user.getLogin(), Arrays.asList("GERENTE"));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new LoginResponse(token));
	}

	private static class UserLogin {

		public String login;
		public String senha;
	}

	@SuppressWarnings("unused")
	private static class LoginResponse {

		public String token;

		public LoginResponse(final String token) {
			this.token = token;
		}
	}
}
