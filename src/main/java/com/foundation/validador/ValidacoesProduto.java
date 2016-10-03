package com.foundation.validador;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.exception.ValidacaoException;
import com.foundation.model.Produto;
import com.foundation.validacao.ValidacaoCampoObrigatorio;
import com.foundation.validacao.Validacoes;

@Component
@RequestScope
public class ValidacoesProduto extends Validacoes {

	private static final long serialVersionUID = 1L;

	public void validarSalvar(Produto produto) {
		if (produto.getNome() == null || StringUtils.isEmpty(produto.getNome().trim())) {
			adicionarValidacao(new ValidacaoCampoObrigatorio("nome", "Campo Nome Obrigatório."));
		}
		
		if(hasValidacoes()) {
			throw new ValidacaoException(this);
		}
	}
}
