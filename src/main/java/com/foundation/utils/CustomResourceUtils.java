package com.foundation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.foundation.exporter.CustomReportExporter;

public final class CustomResourceUtils {

	public static File getResourceFile(String filePath){
		ClassLoader classLoader = CustomReportExporter.class.getClassLoader();
		return new File(classLoader.getResource(filePath).getFile());
	}
	
	public static InputStream getResourceAsStream(String filePath) throws FileNotFoundException{
		ClassLoader classLoader = CustomReportExporter.class.getClassLoader();
		return new FileInputStream(classLoader.getResource(filePath).getFile());
	}
}
