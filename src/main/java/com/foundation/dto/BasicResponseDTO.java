package com.foundation.dto;

public class BasicResponseDTO {

	private Object obj;
	private String mensagem;
	
	public BasicResponseDTO() { }

	public BasicResponseDTO(Object obj) {
		super();
		this.obj = obj;
	}
	
	public BasicResponseDTO(String mensagem) {
		super();
		this.mensagem = mensagem;
	}
	
	public BasicResponseDTO(Object obj, String mensagem) {
		super();
		this.obj = obj;
		this.mensagem = mensagem;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
