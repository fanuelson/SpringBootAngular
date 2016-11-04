package com.foundation.validador;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.model.Composicao;
import com.foundation.service.AbstractService;

@Component
@RequestScope
public class ValidadorComposicao extends AbstractValidador {

	public void validarSalvar(List<Composicao> composicoes, AbstractService abstractService) {
		if(composicoes==null || composicoes.isEmpty()) {
			abstractService.getValidacoes().adicionarValidacaoRegraNegocio("Produto deve ser composto por pelo menos 1 insumo.");
		} else {
			for (Composicao composicao : composicoes) {
				if (composicao.getProduto() == null) {
					abstractService.getValidacoes().adicionarValidacaoCampoObrigatorio("produto", "Produto é Obrigatório.");
				}
				
				if (composicao.getInsumo() == null) {
					abstractService.getValidacoes().adicionarValidacaoCampoObrigatorio("insumo", "Produto é Obrigatório.");
				}
				
				if (composicao.getGasto() == null) {
					abstractService.getValidacoes().adicionarValidacaoCampoObrigatorio("gasto", "Gasto é Obrigatório.");
				}else if (composicao.getGasto().compareTo(new BigDecimal(0)) < 0) {
					abstractService.getValidacoes().adicionarValidacaoRegraNegocio("Gasto de insumo deve ser maior que 0.");
				} 
				
			}
		}
		
	}
}
