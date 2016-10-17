package com.foundation.validacao;

import java.io.Serializable;

public class ValidacaoCampoInvalido implements Validacao, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeCampo;
	private String mensagem;

	public ValidacaoCampoInvalido(String nomeCampo, String mensagem) {
		this.nomeCampo = nomeCampo;
		this.mensagem = mensagem;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((nomeCampo == null) ? 0 : nomeCampo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidacaoCampoInvalido other = (ValidacaoCampoInvalido) obj;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (nomeCampo == null) {
			if (other.nomeCampo != null)
				return false;
		} else if (!nomeCampo.equals(other.nomeCampo))
			return false;
		return true;
	}
	
}
