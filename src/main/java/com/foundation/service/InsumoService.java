package com.foundation.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.dao.InsumoDAO;
import com.foundation.exporter.CustomReportExporter;
import com.foundation.exporter.DocumentExportedVO;
import com.foundation.filtroConsulta.FiltroConsultaInsumo;
import com.foundation.model.Composicao;
import com.foundation.model.Insumo;
import com.foundation.reportVO.ReportInsumoVO;
import com.foundation.utils.CustomCollectionUtils;
import com.foundation.validador.ValidadorInsumoBuilder;

@Service
@RequestScope
public class InsumoService extends AbstractService {

	@Autowired
	private InsumoDAO insumoDAO;
	
	@Autowired
	private ComposicaoService composicaoService;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Page<Insumo> findAll(Pageable page) {
		return insumoDAO.findAll(page);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Page<Insumo> findByFilter(FiltroConsultaInsumo filtro, Pageable page) {
		return insumoDAO.findByFilter(filtro, page);
	}
	
	public Insumo save(Insumo insumo) {
		ValidadorInsumoBuilder.newInstance()
			.validarCampoObrigatorio("nome", insumo.getNome())
			.validarCampoObrigatorio("quantidade", insumo.getQuantidade())
			.validarCampoObrigatorio("medida", insumo.getMedida())
			.assertValid();
		return insumoDAO.save(insumo);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Insumo> findAll() {
		return CustomCollectionUtils.toList(insumoDAO.findAll());
	}

	public void delete(Long id) {
		ValidadorInsumoBuilder.newInstance()
			.validarExisteProdutoCompostoPorInsumo(id, this)
			.assertValid();
		insumoDAO.delete(id);
	}

	public Insumo findOne(Long id) {
		return insumoDAO.findOne(id);
	}
	
	public boolean existeProdutoCompostoPeloInsumo(Long idInsumo) {
		List<Composicao> composicoesAssociadas = composicaoService.findAllByInsumo(idInsumo);
		return CollectionUtils.isNotEmpty(composicoesAssociadas);
	}
	
	public DocumentExportedVO createDocumentReportInsumo() {
		ReportInsumoVO report = new ReportInsumoVO(this.insumoDAO.findAllSubReportVo());
		report.setSubReportDir("/Users/fanuca/Documents/workspace/SpringAngularApp/src/main/resources/reports/insumo/");
		report.setTitulo("Relatório de Insumos");
		report.setDataEmissao(new Date());
		return CustomReportExporter.exportReportToPdf(report);
	}
	
}