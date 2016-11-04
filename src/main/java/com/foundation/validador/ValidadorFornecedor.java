package com.foundation.validador;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.model.Fornecedor;
import com.foundation.service.FornecedorService;

import br.com.any.utils.ValidadorUtils;

@Component
@RequestScope
public class ValidadorFornecedor extends AbstractValidador {

	public void validarSalvar(Fornecedor fornecedor, FornecedorService fornecedorService) {
		validarCamposObrigatorios(fornecedor, fornecedorService);
		validarCpfCnpj(fornecedor, fornecedorService);
	}

	private void validarCpfCnpj(Fornecedor fornecedor, FornecedorService fornecedorService) {
		String cpfCnpj = fornecedor.getCpfCnpj();
		if(StringUtils.isNotEmpty(cpfCnpj)){
			if (fornecedorService.existsFornecedorWithCpfCnpj(cpfCnpj)) {
				fornecedorService.getValidacoes().adicionarValidacaoRegraNegocio("CPF/CNPJ já cadastrado.");
			} else if (!ValidadorUtils.isCNPJ(cpfCnpj) && !ValidadorUtils.isCPF(cpfCnpj)) {
				fornecedorService.getValidacoes().adicionarValidacaoCampoInvalido("cpfCnpj", "CPF/CNPJ Inválido");
			}
			
		}
	}

	private void validarCamposObrigatorios(Fornecedor fornecedor, FornecedorService fornecedorService) {
		if(StringUtils.isBlank(fornecedor.getNome())) {
			fornecedorService.getValidacoes().adicionarValidacaoCampoObrigatorio("nome", "Campo Nome obrigatório.");
		}
	}
}
