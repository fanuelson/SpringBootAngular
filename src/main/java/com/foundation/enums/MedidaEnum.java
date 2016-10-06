package com.foundation.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MedidaEnum {

	ml("ml", "Mililitro"),
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
	
	public static MedidaEnum getByAbreviacao(String abreviacao) {
		if(StringUtils.isNotBlank(abreviacao)){
			for (MedidaEnum medida : values()) {
				if(medida.getAbreviacao().equals(abreviacao)) {
					return medida;
				}
			}
		}
	
		return null;
	}
	
	@JsonCreator
    public static MedidaEnum forValue(String value) {
		return getByAbreviacao(value);
    }
	
}
