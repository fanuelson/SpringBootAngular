package com.foundation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.InsumoDAO;
import com.foundation.filtroConsulta.FiltroConsultaInsumo;
import com.foundation.model.Insumo;
import com.foundation.utils.CollectionUtils;
import com.foundation.validador.ValidadorInsumo;

@Service
@RequestScope
public class InsumoService {

	@Autowired
	private InsumoDAO insumoDAO;
	
	@Autowired
	private ValidadorInsumo validacoes;
	
	public Page<Insumo> findAll(Pageable page) {
		return insumoDAO.findAll(page);
	}
	
	public Page<Insumo> findByFilter(FiltroConsultaInsumo filtro, Pageable page) {
		return insumoDAO.findByFilter(filtro, page);
	}
	
	public Insumo save(Insumo insumo) {
		validacoes.validarSalvar(insumo);
		return insumoDAO.save(insumo);
	}

	public List<Insumo> findAll() {
		return CollectionUtils.toList(insumoDAO.findAll());
	}
	
}