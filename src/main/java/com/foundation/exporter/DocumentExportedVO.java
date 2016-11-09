package com.foundation.exporter;

import java.io.InputStream;

public class DocumentExportedVO {

	private String fileName = "relatorio.pdf";
	
	private byte[] byteArray;
	
	private InputStream inputStream;

	public DocumentExportedVO() {
		super();
	}

	public DocumentExportedVO(byte[] byteArray, InputStream inputStream) {
		super();
		this.byteArray = byteArray;
		this.inputStream = inputStream;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
