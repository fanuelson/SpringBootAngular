package com.foundation.validador;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.model.Insumo;
import com.foundation.service.InsumoService;
import com.foundation.validacao.ValidacaoCampoObrigatorio;
import com.foundation.validacao.Validacoes;

@Component
@RequestScope
public class ValidadorInsumo {

	public void validarSalvar(Insumo insumo, InsumoService insumoService) {
		Validacoes validacoes = insumoService.getValidacoes();
		if (insumo.getNome() == null || StringUtils.isEmpty(insumo.getNome().trim())) {
			validacoes.adicionarValidacao(new ValidacaoCampoObrigatorio("nome", "Campo Nome Obrigatório."));
		}
		if (insumo.getQuantidade() == null) {
			validacoes.adicionarValidacao(new ValidacaoCampoObrigatorio("quantidade", "Campo Quantidade Obrigatório."));
		}
		if (insumo.getMedida() == null) {
			validacoes.adicionarValidacao(new ValidacaoCampoObrigatorio("medida", "Campo Medida Obrigatório."));
		}
		
	}
}
