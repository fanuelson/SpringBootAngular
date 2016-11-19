package com.foundation.aspect;

import java.lang.reflect.Method;

import javax.inject.Inject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.foundation.annotation.NeedsCaixa;
import com.foundation.enums.CaixaStatus;
import com.foundation.exception.ValidacaoException;
import com.foundation.service.CaixaService;
import com.foundation.validacao.Validacoes;

@Aspect
@Component
public class CaixaAspect {
	
	@Inject
	private CaixaService caixaService;
	
	@Around(value = "@annotation(com.foundation.annotation.NeedsCaixa)")
	public Object proceedIfCaixaHasAnnotatedStatus(ProceedingJoinPoint pjp) throws Throwable {
		boolean existeCaixaAberto = caixaService.existeCaixaAberto();
		CaixaStatus neededStatus = getAnnot(pjp).status();
		if (neededStatus.equals(CaixaStatus.ABERTO) && !existeCaixaAberto) {
			throw new ValidacaoException(createValidacoes("É necessário que o caixa esteja aberto."));
		} else if (neededStatus.equals(CaixaStatus.FECHADO) && existeCaixaAberto) {
			throw new ValidacaoException(createValidacoes("É necessário que o caixa esteja fechado."));
		}
		return pjp.proceed();
	}
	
	private Validacoes createValidacoes(String mensagem) {
		Validacoes validacoes = Validacoes.newInstance();
		validacoes.adicionarValidacaoRegraNegocio(mensagem);
		return validacoes;
	}
	
	private NeedsCaixa getAnnot(ProceedingJoinPoint pjp) {
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method m = ms.getMethod();
		return m.getDeclaredAnnotation(NeedsCaixa.class);
	}
}
