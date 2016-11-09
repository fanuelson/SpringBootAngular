package com.foundation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ReportExporterExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ReportExporterException.class)
	public ResponseEntity<?> validacaoExceptionHandler(ReportExporterException ve) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ve.getMessage());
	}

}
