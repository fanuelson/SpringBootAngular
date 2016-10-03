package com.foundation.enums;

public enum MedidaEnum {

	ML("ml", "Mililitro"),
	L("L", "Litro"),
	g("g","Grama"),
	kg("kg", "Kilograma"),
	Uni("u", "Unidade");
	
	private String abreviacao;
	private String descricao;
	
	private MedidaEnum(String abreviacao, String descricao) {
		this.abreviacao = abreviacao;
		this.descricao = descricao;
	}

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
