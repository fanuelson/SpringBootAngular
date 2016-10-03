package com.foundation.exception;

import com.foundation.validacao.Validacoes;

public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Validacoes validacoes;
	
	public ValidacaoException(Validacoes validacoes) {
		this.validacoes = validacoes;
	}

	public Validacoes getValidacoes() {
		return validacoes;
	}

	public void setValidacoes(Validacoes validacoes) {
		this.validacoes = validacoes;
	}
	
}
