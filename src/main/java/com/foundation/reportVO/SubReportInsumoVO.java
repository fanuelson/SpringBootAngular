package com.foundation.reportVO;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import com.foundation.model.Insumo;

public class SubReportInsumoVO {

	private String nome;
	private String descricao;
	private BigDecimal quantidade;
	private String medida;
	
	public SubReportInsumoVO() {
		super();
	}

	public SubReportInsumoVO(Insumo ins) {
		super();
		this.nome = ins.getNome();
		this.descricao = ins.getDescricao();
		this.quantidade = ins.getQuantidade();
		this.medida = ins.getMedida().getAbreviacao();
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public String getMedida() {
		return medida;
	}
	
	public String getQuantidadeFormatada() {
		DecimalFormatSymbols s = new DecimalFormatSymbols();
		s.setDecimalSeparator(',');
		s.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("#,##0.00", s);
		return df.format(this.quantidade);
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}
	
}
