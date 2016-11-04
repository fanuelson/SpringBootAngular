package com.foundation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.foundation.validacao.Validacoes;


@ControllerAdvice
public class ValidacaoExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<?> validacaoExceptionHandler(ValidacaoException ve) {
		return new ResponseEntity<Validacoes>(ve.getValidacoes(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
