package com.foundation.validador;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class ValidadorProdutoBuilder extends AbstractValidadorBuilder {

	public static ValidadorProdutoBuilder newInstance() {
		return new ValidadorProdutoBuilder();
	}
	
}
