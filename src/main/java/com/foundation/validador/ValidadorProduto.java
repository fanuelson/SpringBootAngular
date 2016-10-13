package com.foundation.validador;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.exception.ValidacaoException;
import com.foundation.model.Produto;
import com.foundation.validacao.ValidacaoCampoObrigatorio;
import com.foundation.validacao.Validacoes;

@Component
@RequestScope
public class ValidadorProduto extends Validacoes {

	private static final long serialVersionUID = 1L;

	public void validarSalvar(final Produto produto) {
		if (StringUtils.isBlank(produto.getNome())) {
			adicionarValidacao(new ValidacaoCampoObrigatorio("nome", "Campo Nome Obrigatório."));
		}
		
		if(hasValidacoes()) {
			throw new ValidacaoException(this);
		}
	}
}
