package com.foundation.exporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.foundation.exception.ReportExporterException;
import com.foundation.reportVO.AbstractReportVO;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class CustomReportExporter {

	public static DocumentExportedVO exportReportToPdf(AbstractReportVO reportVO) {
		try {
			JasperPrint print = getJasperPrint(reportVO);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = getJRPdfExporter(print, baos);
			exporter.exportReport();
			return new DocumentExportedVO(baos.toByteArray(), new ByteArrayInputStream(baos.toByteArray()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ReportExporterException("Ocorreu um erro ao gerar o relatório.");
		}
	}

	private static JRPdfExporter getJRPdfExporter(JasperPrint print, ByteArrayOutputStream baos) {
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		return exporter;
	}

	private static JasperPrint getJasperPrint(AbstractReportVO reportVO)
			throws FileNotFoundException, JRException {
		FileInputStream fis = reportVO.getReportTemplate();
		JasperPrint print = JasperFillManager.fillReport(fis, reportVO.getReportParams(), new JREmptyDataSource());
		return print;
	}
	
}
