package com.foundation.validacao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public abstract class Validacoes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Set<Validacao> validacoes = new HashSet<>();
	
	public Set<Validacao> getValidacoes() {
		return validacoes;
	}
	
	public void adicionarValidacao(Validacao validacao) {
		getValidacoes().add(validacao);
	}
	
	public void limparValidacoes() {
		getValidacoes().clear();
	}
	
	public boolean hasValidacoes() {
		return !getValidacoes().isEmpty();
	}
	
}
