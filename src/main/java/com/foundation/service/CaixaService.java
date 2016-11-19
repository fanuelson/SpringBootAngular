package com.foundation.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.foundation.dao.CaixaDAO;
import com.foundation.model.Caixa;

@Service
public class CaixaService extends AbstractService{

	@Inject
	private CaixaDAO caixaDAO;
	
	public Caixa findCaixaAberto() {
		return caixaDAO.findCaixaAberto();
	}
	
	public boolean existeCaixaAberto() {
		return findCaixaAberto() != null;
	}
}
