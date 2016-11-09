package com.foundation.reportVO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.foundation.annotation.ReportParam;
import com.foundation.annotation.ReportTemplateModel;
import com.foundation.utils.CustomResourceUtils;

import br.com.any.utils.ReflectionUtils;

public abstract class AbstractReportVO {

	public Map<String, Object> getReportParams() {
		Map<String, Object> params = new HashMap<>();
		List<Field> campos = ReflectionUtils.recuperarCamposAnotadosCom(getClass(), ReportParam.class);
		for (Field field : campos) {
			String paramName = getParamName(field);
			Object paramValue = getParamValue(field); 
			params.put(paramName, paramValue);
		}
		return params;
	}
	
	public FileInputStream getReportTemplate() {
		boolean isUnderResourceClassPath = getClass().getDeclaredAnnotation(ReportTemplateModel.class).isTemplateUnderResource();
		String templatePath = getClass().getDeclaredAnnotation(ReportTemplateModel.class).templatePath();
		try{
			if(isUnderResourceClassPath) {
				return new FileInputStream(CustomResourceUtils.getResourceFile(templatePath));
			}else{
				return new FileInputStream(templatePath);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getParamName(Field f) {
		String paramName = f.getDeclaredAnnotation(ReportParam.class).name();
		return StringUtils.isBlank(paramName) ? f.getName() : paramName;
	}
	
	private Object getParamValue(Field f) {
		return ReflectionUtils.recuperarValorCampo(this, f.getName());
	}
}
