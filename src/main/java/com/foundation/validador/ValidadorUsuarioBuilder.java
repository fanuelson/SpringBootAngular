package com.foundation.validador;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class ValidadorUsuarioBuilder extends AbstractValidadorBuilder {

	public static ValidadorUsuarioBuilder newInstance() {
		return new ValidadorUsuarioBuilder();
	}
	
}
