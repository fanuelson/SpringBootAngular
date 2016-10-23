package com.foundation.validador;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.exception.ValidacaoException;
import com.foundation.model.Fornecedor;
import com.foundation.service.FornecedorService;
import com.foundation.validacao.ValidacaoCampoInvalido;
import com.foundation.validacao.ValidacaoCampoObrigatorio;
import com.foundation.validacao.ValidacaoRegraNegocio;
import com.foundation.validacao.Validacoes;

import br.com.any.utils.ValidadorUtils;

@Component
@RequestScope
public class ValidadorFornecedor extends Validacoes {

	private static final long serialVersionUID = 1L;

	public void validarSalvar(Fornecedor fornecedor, FornecedorService fornecedorService) {
		validarCamposObrigatorios(fornecedor);
		validarCpfCnpj(fornecedor, fornecedorService);
		
		if(hasValidacoes()) {
			throw new ValidacaoException(this);
		}
	}

	private void validarCpfCnpj(Fornecedor fornecedor, FornecedorService fornecedorService) {
		String cpfCnpj = fornecedor.getCpfCnpj();
		if(StringUtils.isNotEmpty(cpfCnpj)){
			if (fornecedorService.existsFornecedorWithCpfCnpj(cpfCnpj)) {
				adicionarValidacao(new ValidacaoRegraNegocio("CPF/CNPJ já cadastrado."));
			} else if (!ValidadorUtils.isCNPJ(cpfCnpj) && !ValidadorUtils.isCPF(cpfCnpj)) {
				adicionarValidacao(new ValidacaoCampoInvalido("cpfCnpj", "CPF/CNPJ Inválido"));
			}
			
		}
	}

	private void validarCamposObrigatorios(Fornecedor fornecedor) {
		if(StringUtils.isBlank(fornecedor.getNome())) {
			adicionarValidacao(new ValidacaoCampoObrigatorio("nome", "Campo Nome obrigatório."));
		}
	}
}
