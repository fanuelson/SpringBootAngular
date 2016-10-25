package com.foundation.validador;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.model.Produto;
import com.foundation.service.ProdutoService;
import com.foundation.validacao.ValidacaoCampoObrigatorio;

@Component
@RequestScope
public class ValidadorProduto {

	public void validarSalvar(final Produto produto, ProdutoService produtoService) {
		if (StringUtils.isBlank(produto.getNome())) {
			produtoService.getValidacoes().adicionarValidacao(new ValidacaoCampoObrigatorio("nome", "Campo Nome Obrigatório."));
		}
		
	}
}
