package com.foundation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.exception.ValidacaoException;
import com.foundation.validacao.Validacoes;

@Service
@RequestScope
public abstract class AbstractService {

	@Autowired
	private Validacoes validacoes;
	
	public Validacoes getValidacoes(){
		return this.validacoes;
	}

	protected void assertValid(){
		if(getValidacoes().hasValidacoes()) {
			throw new ValidacaoException(getValidacoes());
		}
	}

	protected void limparValidacoes(){
		getValidacoes().limparValidacoes();
	}
}
