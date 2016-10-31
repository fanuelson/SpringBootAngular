package com.foundation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.InsumoDAO;
import com.foundation.filtroConsulta.FiltroConsultaInsumo;
import com.foundation.model.Insumo;
import com.foundation.utils.CollectionUtils;
import com.foundation.validador.ValidadorInsumo;

@Service
@RequestScope
public class InsumoService extends AbstractService {

	@Autowired
	private InsumoDAO insumoDAO;
	
	@Autowired
	private ValidadorInsumo validacoes;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Page<Insumo> findAll(Pageable page) {
		return insumoDAO.findAll(page);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Page<Insumo> findByFilter(FiltroConsultaInsumo filtro, Pageable page) {
		return insumoDAO.findByFilter(filtro, page);
	}
	
	public Insumo save(Insumo insumo) {
		limparValidacoes();
		validacoes.validarSalvar(insumo, this);
		assertValid();
		return insumoDAO.save(insumo);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Insumo> findAll() {
		return CollectionUtils.toList(insumoDAO.findAll());
	}
	
	public void delete(Insumo insumo) {
		insumoDAO.delete(insumo);
	}

	public void delete(Long id) {
		insumoDAO.delete(id);
	}

	public Insumo findOne(Long id) {
		return insumoDAO.findOne(id);
	}
	
}