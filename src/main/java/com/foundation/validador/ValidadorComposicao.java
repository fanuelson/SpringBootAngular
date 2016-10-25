package com.foundation.validador;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.model.Composicao;
import com.foundation.validacao.ValidacaoCampoObrigatorio;
import com.foundation.validacao.ValidacaoRegraNegocio;
import com.foundation.validacao.Validacoes;

@Component
@RequestScope
public class ValidadorComposicao {

	public void validarSalvar(List<Composicao> composicoes, Validacoes validacoes) {
		if(composicoes==null || composicoes.isEmpty()) {
			validacoes.adicionarValidacao(new ValidacaoRegraNegocio("Produto deve ser composto por pelo menos 1 insumo."));
		} else {
			for (Composicao composicao : composicoes) {
				if (composicao.getProduto() == null) {
					validacoes.adicionarValidacao(new ValidacaoCampoObrigatorio("produto", "Produto é Obrigatório."));
				}
				
				if (composicao.getInsumo() == null) {
					validacoes.adicionarValidacao(new ValidacaoCampoObrigatorio("insumo", "Produto é Obrigatório."));
				}
				
				if (composicao.getGasto() == null) {
					validacoes.adicionarValidacao(new ValidacaoCampoObrigatorio("gasto", "Gasto é Obrigatório."));
				}else if (composicao.getGasto().compareTo(new BigDecimal(0)) < 0) {
					validacoes.adicionarValidacao(new ValidacaoRegraNegocio("Gasto de insumo deve ser maior que 0."));
				} 
				
			}
		}
		
	}
}
