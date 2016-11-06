package com.foundation.validador;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.service.InsumoService;

@Component
@RequestScope
public class ValidadorInsumoBuilder extends AbstractValidadorBuilder {
	
	public ValidadorInsumoBuilder(){
		super();
	}
	
	public static ValidadorInsumoBuilder newInstance() {
		return new ValidadorInsumoBuilder();
	}
	
	public ValidadorInsumoBuilder validarExisteProdutoCompostoPorInsumo(Long idInsumo, InsumoService insumoService){
		if(insumoService.existeProdutoCompostoPeloInsumo(idInsumo)) {
			getValidacoes().adicionarValidacaoRegraNegocio("Não é possível remover o Insumo pois o mesmo se encontra associado a um Produto.");
		}
		return this;
	}
}
