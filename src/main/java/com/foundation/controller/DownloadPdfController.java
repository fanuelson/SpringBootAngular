package com.foundation.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("pdf")
@RequestScope
public class DownloadPdfController {

	private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
	
	private static HttpHeaders getHttpHeadersForDownload(String fileName) {
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	    headers.add("Pragma", "no-cache");
	    headers.add("Expires", "0");
	    headers.add("Content-disposition", "attachment;filename="+fileName);
	    return headers;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> downloadPDFFile()
	        throws IOException {

	    ClassPathResource pdfFile = new ClassPathResource("test.pdf");
	    
	    return ResponseEntity
	            .ok()
	            .headers(getHttpHeadersForDownload("testando.pdf"))
	            .contentLength(pdfFile.contentLength())
	            .contentType(MediaType.parseMediaType(APPLICATION_OCTET_STREAM))
	            .body(new InputStreamResource(pdfFile.getInputStream()));
	}
}
