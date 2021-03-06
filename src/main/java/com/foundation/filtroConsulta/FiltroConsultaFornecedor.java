package com.foundation.filtroConsulta;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.foundation.config.Constants;

public class FiltroConsultaFornecedor {

	private String nome;

	private String cpfCnpj;

	private Long celular;

	private Long telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
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
		return StringUtils.wrap(StringUtils.trimToNull(this.nome), Constants.PERCENT);
	}

	public String getCpfCnpjLike() {
		return StringUtils.wrap(StringUtils.trimToNull(this.cpfCnpj), Constants.PERCENT);
	}

	public String getCelularLike() {
		return StringUtils.wrap(Objects.toString(this.celular, null), Constants.PERCENT);
	}

	public String getTelefoneLike() {
		return StringUtils.wrap(Objects.toString(this.telefone, null), Constants.PERCENT);
	}

}
