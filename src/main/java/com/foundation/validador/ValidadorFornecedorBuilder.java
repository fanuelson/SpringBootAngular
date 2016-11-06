package com.foundation.validador;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.model.Fornecedor;
import com.foundation.service.FornecedorService;

import br.com.any.utils.ValidadorUtils;

@Component
@RequestScope
public class ValidadorFornecedorBuilder extends AbstractValidadorBuilder {

	public static ValidadorFornecedorBuilder newInstance() {
		return new ValidadorFornecedorBuilder();
	}
	
	public ValidadorFornecedorBuilder validarCpfCnpj(Fornecedor fornecedor, FornecedorService fornecedorService) {
		String cpfCnpj = fornecedor.getCpfCnpj();
		if(StringUtils.isNotEmpty(cpfCnpj)){
			if (fornecedorService.existsFornecedorWithCpfCnpj(cpfCnpj)) {
				getValidacoes().adicionarValidacaoRegraNegocio("CPF/CNPJ já cadastrado.");
			} else if (!ValidadorUtils.isCNPJ(cpfCnpj) && !ValidadorUtils.isCPF(cpfCnpj)) {
				getValidacoes().adicionarValidacaoCampoInvalido("cpfCnpj", "CPF/CNPJ Inválido");
			}
			
		}
		return this;
	}

}
