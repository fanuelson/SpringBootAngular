package com.foundation.reportVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.foundation.annotation.ReportParam;
import com.foundation.annotation.ReportTemplateModel;

@ReportTemplateModel(templatePath = "reports/insumo/relatorioInsumos.jasper")
public class ReportInsumoVO extends AbstractReportVO {
	
	@ReportParam
	private String titulo;
	
	@ReportParam
	private Date dataEmissao;

	@ReportParam(name = "SUBREPORT_DIR")
	private String subReportDir;
	
	@ReportParam
	private List<SubReportInsumoVO> insumos = new ArrayList<>();
	
	public ReportInsumoVO() { }
	
	public ReportInsumoVO(List<SubReportInsumoVO> insumos) {
		super();
		this.insumos = insumos;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public List<SubReportInsumoVO> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<SubReportInsumoVO> insumos) {
		this.insumos = insumos;
	}

	public String getSubReportDir() {
		return subReportDir;
	}

	public void setSubReportDir(String subReportDir) {
		this.subReportDir = subReportDir;
	}
	
}
