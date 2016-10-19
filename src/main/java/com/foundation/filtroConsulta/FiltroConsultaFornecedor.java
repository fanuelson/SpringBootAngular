package com.foundation.filtroConsulta;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class FiltroConsultaFornecedor {

	private static final String ASPAS = "%";

	private String nome;

	private Long cpfCnpj;

	private Long celular;

	private Long telefone;

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

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getNomeLike() {
		return StringUtils.wrap(this.nome, ASPAS);
	}

	public String getCpfCnpjLike() {
		return StringUtils.wrap(Objects.toString(this.cpfCnpj), ASPAS);
	}

	public String getCelularLike() {
		return StringUtils.wrap(Objects.toString(this.celular, null), ASPAS);
	}

	public String getTelefoneLike() {
		return StringUtils.wrap(Objects.toString(this.telefone, null), ASPAS);
	}

}
