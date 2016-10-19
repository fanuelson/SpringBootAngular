package com.foundation.filtroConsulta;

import org.apache.commons.lang3.StringUtils;

public class FiltroConsultaFornecedor {

	private static final String ASPAS = "%";
	
	private String nome;
	
	private Long cpfCnpj;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(Long cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomeLike() {
		return StringUtils.wrap(this.nome, ASPAS);
	}
	
	public String getCpfCnpjLike() {
		if(this.cpfCnpj != null) {
			return StringUtils.wrap(String.valueOf(this.cpfCnpj), ASPAS);
		}
		return null;
	}
	
}
