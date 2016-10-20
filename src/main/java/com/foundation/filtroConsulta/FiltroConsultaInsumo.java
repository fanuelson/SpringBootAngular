package com.foundation.filtroConsulta;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.foundation.enums.MedidaEnum;

public class FiltroConsultaInsumo {

	private static final String ASPAS = "%";

	private String nomeOrDescricao;

	private BigDecimal quantidadeMin;

	private BigDecimal quantidadeMax;

	private MedidaEnum medida;

	public String getNomeOrDescricaoLike() {
		return StringUtils.wrap(StringUtils.trimToNull(this.nomeOrDescricao), ASPAS);
	}

	public String getNomeOrDescricao() {
		return nomeOrDescricao;
	}

	public void setNomeOrDescricao(String nomeOrDescricao) {
		this.nomeOrDescricao = nomeOrDescricao;
	}

	public BigDecimal getQuantidadeMin() {
		return quantidadeMin;
	}

	public void setQuantidadeMin(BigDecimal quantidadeMin) {
		this.quantidadeMin = quantidadeMin;
	}

	public BigDecimal getQuantidadeMax() {
		return quantidadeMax;
	}

	public void setQuantidadeMax(BigDecimal quantidadeMax) {
		this.quantidadeMax = quantidadeMax;
	}

	public MedidaEnum getMedida() {
		return medida;
	}

	public void setMedida(MedidaEnum medida) {
		this.medida = medida;
	}

}
