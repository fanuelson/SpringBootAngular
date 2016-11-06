package com.foundation.validador;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.model.Composicao;

@Component
@RequestScope
public class ValidadorComposicaoBuilder extends AbstractValidadorBuilder {

	public static ValidadorComposicaoBuilder newInstance(){
		return new ValidadorComposicaoBuilder();
	}
	
	public ValidadorComposicaoBuilder validarComposicoes(List<Composicao> composicoes) {
		if(composicoes==null || composicoes.isEmpty()) {
			getValidacoes().adicionarValidacaoRegraNegocio("Produto deve ser composto por pelo menos 1 insumo.");
		} else {
			for (Composicao composicao : composicoes) {
				if (composicao.getProduto() == null) {
					getValidacoes().adicionarValidacaoCampoObrigatorio("produto", "Produto é Obrigatório.");
				}
				
				if (composicao.getInsumo() == null) {
					getValidacoes().adicionarValidacaoCampoObrigatorio("insumo", "Produto é Obrigatório.");
				}
				
				if (composicao.getGasto() == null) {
					getValidacoes().adicionarValidacaoCampoObrigatorio("gasto", "Gasto é Obrigatório.");
				}else if (composicao.getGasto().compareTo(new BigDecimal(0)) < 0) {
					getValidacoes().adicionarValidacaoRegraNegocio("Gasto de insumo deve ser maior que 0.");
				} 
				
			}
		}
		return this;
	}
}
