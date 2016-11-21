package com.foundation.filtroConsulta;

import org.apache.commons.lang3.StringUtils;

import com.foundation.config.Constants;
import com.foundation.enums.AtivoInativoEnum;

public class FiltroConsultaProduto {

	private Long idProduto;
	
	private String nome;
	
	private AtivoInativoEnum status;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}
	
	public String getNomeLike() {
		return StringUtils.wrap(StringUtils.trimToNull(this.nome), Constants.PERCENT);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public AtivoInativoEnum getStatus() {
		return status;
	}

	public void setStatus(AtivoInativoEnum status) {
		this.status = status;
	}
	
}
