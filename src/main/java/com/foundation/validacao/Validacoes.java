package com.foundation.validacao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import com.foundation.exception.ValidacaoException;

@Component
public class Validacoes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Set<String> validacoesRegraNegocio = new HashSet<>();
	private HashMap<String, String> camposObrigatorios = new HashMap<>();
	private HashMap<String, String> camposInvalidos = new HashMap<>();
	
	public static Validacoes newInstance() {
		return new Validacoes();
	}
	
	public void adicionarValidacaoRegraNegocio(String validacao) {
		this.validacoesRegraNegocio.add(validacao);
	}
	
	public void adicionarValidacaoCampoObrigatorio(String nomeCampo, String mensagem) {
		this.camposObrigatorios.put(nomeCampo, mensagem);
	}
	
	public void adicionarValidacaoCampoInvalido(String nomeCampo, String mensagem) {
		this.camposInvalidos.put(nomeCampo, mensagem);
	}
	
	public void adicionarValidacoes(List<String> validacoes) {
		for (String string : validacoes) {
			this.validacoesRegraNegocio.add(string);
		}
	}
	
	public void limparValidacoes() {
		this.validacoesRegraNegocio.clear();
		this.camposObrigatorios.clear();
		this.camposInvalidos.clear();
	}
	
	public boolean hasValidacoes() {
		return CollectionUtils.isNotEmpty(this.validacoesRegraNegocio)
				|| MapUtils.isNotEmpty(this.camposObrigatorios)
				|| MapUtils.isNotEmpty(this.camposInvalidos);
	}
	
	public void assertValid(){
		if(this.hasValidacoes()) {
			throw new ValidacaoException(this);
		}
	}

	public HashMap<String, String> getCamposObrigatorios() {
		return this.camposObrigatorios;
	}

	public HashMap<String, String> getCamposInvalidos() {
		return this.camposInvalidos;
	}

	public Set<String> getValidacoesRegraNegocio() {
		return this.validacoesRegraNegocio;
	}

}
