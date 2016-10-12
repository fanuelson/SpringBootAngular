package com.foundation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:/Users/fanuca/Documents/spring-angular-app-properties/secret.properties")
public class SecretConfig {

	@Value("${app.secret.key}")
	private String secretKey;

	public String getSecretKey() {
		return this.secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
}
