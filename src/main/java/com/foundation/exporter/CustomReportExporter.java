package com.foundation.exporter;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class CustomReportExporter {

	public static OutputStream exportReport(String templatePath, Map<String, Object> params) {
		try {
			JasperPrint print = getJasperPrint(templatePath, params);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = getJRPdfExporter(print, baos);
			exporter.exportReport();
			return baos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static JRPdfExporter getJRPdfExporter(JasperPrint print, ByteArrayOutputStream baos) {
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		return exporter;
	}

	private static JasperPrint getJasperPrint(String templatePath, Map<String, Object> params)
			throws FileNotFoundException, JRException {
		FileInputStream fis = new FileInputStream(templatePath);
		JasperPrint print = JasperFillManager.fillReport(fis, params, new JREmptyDataSource());
		return print;
	}
	
}
