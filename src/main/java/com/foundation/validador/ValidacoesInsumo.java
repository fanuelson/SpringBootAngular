package com.foundation.validador;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.exception.ValidacaoException;
import com.foundation.model.Insumo;
import com.foundation.validacao.ValidacaoCampoObrigatorio;
import com.foundation.validacao.Validacoes;

@Component
@RequestScope
public class ValidacoesInsumo extends Validacoes {

	private static final long serialVersionUID = 1L;

	public void validarSalvar(Insumo insumo) {
		if (insumo.getNome() == null || StringUtils.isEmpty(insumo.getNome().trim())) {
			adicionarValidacao(new ValidacaoCampoObrigatorio("nome", "Campo Nome Obrigatório."));
		}
		if (insumo.getQuantidade() == null) {
			adicionarValidacao(new ValidacaoCampoObrigatorio("quantidade", "Campo Quantidade Obrigatório."));
		}
		if (insumo.getMedida() == null) {
			adicionarValidacao(new ValidacaoCampoObrigatorio("medida", "Campo Medida Obrigatório."));
		}
		
		if(hasValidacoes()) {
			throw new ValidacaoException(this);
		}
	}
}
