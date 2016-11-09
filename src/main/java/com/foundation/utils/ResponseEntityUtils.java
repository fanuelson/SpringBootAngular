package com.foundation.utils;

import org.springframework.http.HttpHeaders;

public class ResponseEntityUtils {

	public static HttpHeaders getHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	    headers.add("Pragma", "no-cache");
	    headers.add("Expires", "0");
	    headers.add("Content-disposition", "attachment;filename="+fileName);
	    return headers;
	}
	
}
