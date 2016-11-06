package com.foundation.validador;

import org.apache.commons.lang3.StringUtils;

import com.foundation.exception.ValidacaoException;
import com.foundation.validacao.Validacoes;

public abstract class AbstractValidadorBuilder {
	
	private static String TEMPLATE_MENSAGEM_CAMPO_OBRIGATORIO = "Campo %s Obrigatório.";

	private Validacoes validacoes;
	
	public AbstractValidadorBuilder(){
		validacoes = new Validacoes();
	}
	
	public Validacoes getValidacoes(){
		return this.validacoes;
	}
	
	public AbstractValidadorBuilder adicionarValidacoes(Validacoes validacoes) {
		getValidacoes().adicionarValidacoes(validacoes);
		return this;
	}

	public void assertValid(){
		if(getValidacoes().hasValidacoes()) {
			throw new ValidacaoException(getValidacoes());
		}
	}

	public void limparValidacoes(){
		getValidacoes().limparValidacoes();
	}
	
	public AbstractValidadorBuilder validarCampoObrigatorio(String nomeCampo, Object valorCampo) {
		if(valorCampo instanceof String) {
			this.validarCampoObrigatorioString(nomeCampo, (String) valorCampo);
		}else{
			if(valorCampo == null) {
				getValidacoes().adicionarValidacaoCampoObrigatorio(nomeCampo, getMensagemCampoObrigatorio(nomeCampo));
			}
		}
		return this;
	}
	
	public AbstractValidadorBuilder validarCampoObrigatorioString(String nomeCampo, String valorCampo) {
		if(StringUtils.isBlank(StringUtils.stripToNull(valorCampo))){
			getValidacoes().adicionarValidacaoCampoObrigatorio(nomeCampo, getMensagemCampoObrigatorio(nomeCampo));
		}
		return this;
	}
	
	private String getMensagemCampoObrigatorio(String nomeCampo) {
		String nomeCampoCapitalize = StringUtils.capitalize(nomeCampo);
		return String.format(TEMPLATE_MENSAGEM_CAMPO_OBRIGATORIO, nomeCampoCapitalize);
	}
}
