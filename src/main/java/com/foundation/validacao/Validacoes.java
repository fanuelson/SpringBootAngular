package com.foundation.validacao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
public class Validacoes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Set<Validacao> validacoes = new HashSet<>();
	
	public Set<Validacao> getValidacoes() {
		return validacoes;
	}
	
	public void adicionarValidacao(Validacao validacao) {
		getValidacoes().add(validacao);
	}
	
	public void adicionarValidacoes(List<Validacao> validacoes) {
		getValidacoes().addAll(validacoes);
	}
	
	public void limparValidacoes() {
		getValidacoes().clear();
	}
	
	public boolean hasValidacoes() {
		return CollectionUtils.isNotEmpty(getValidacoes());
	}
	
}
