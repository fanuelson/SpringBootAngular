package com.foundation.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AtivoInativoEnum {

	A("A", "Ativo"),
	I("I", "Inativo");
	
	private String id;
	private String descricao;

	private AtivoInativoEnum(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static AtivoInativoEnum getByDescricao(String descricao) {
		if(StringUtils.isNotBlank(descricao)){
			for (AtivoInativoEnum ativoInativo : values()) {
				if(ativoInativo.getDescricao().equals(descricao)) {
					return ativoInativo;
				}
			}
		}
	
		return null;
	}
	
	public static AtivoInativoEnum getById(String id) {
		if(StringUtils.isNotBlank(id)){
			for (AtivoInativoEnum ativoInativo : values()) {
				if(ativoInativo.getId().equals(id)) {
					return ativoInativo;
				}
			}
		}
		
		return null;
	}
	
	@JsonCreator
    public static AtivoInativoEnum forValue(String value) {
		AtivoInativoEnum ativoInativo = getById(value);
		return ativoInativo == null ? getByDescricao(value) : ativoInativo;
    }
	
}
