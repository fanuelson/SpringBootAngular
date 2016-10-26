package com.foundation.validacao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

@Component
public class Validacoes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Set<Validacao> validacoes = new HashSet<>();
	private HashMap<String, String> camposObrigatorios = new HashMap<>();
	private HashMap<String, String> camposInvalidos = new HashMap<>();
	
	public void adicionarValidacao(Validacao validacao) {
		getValidacoes().add(validacao);
	}
	
	public void adicionarValidacaoCampoObrigatorio(String nomeCampo, String mensagem) {
		camposObrigatorios.put(nomeCampo, mensagem);
	}
	
	public void adicionarValidacaoCampoInvalido(String nomeCampo, String mensagem) {
		camposInvalidos.put(nomeCampo, mensagem);
	}
	
	public void adicionarValidacoes(List<Validacao> validacoes) {
		getValidacoes().addAll(validacoes);
	}
	
	public void limparValidacoes() {
		getValidacoes().clear();
		camposObrigatorios.clear();
		camposInvalidos.clear();
	}
	
	public boolean hasValidacoes() {
		return CollectionUtils.isNotEmpty(getValidacoes())
				|| MapUtils.isNotEmpty(camposObrigatorios)
				|| MapUtils.isNotEmpty(camposInvalidos);
	}
	
	private Set<Validacao> getValidacoes() {
		return validacoes;
	}
	
	public HashMap<String, String> getCamposObrigatorios() {
		return camposObrigatorios;
	}

	public HashMap<String, String> getCamposInvalidos() {
		return camposInvalidos;
	}

	
}
