package com.foundation;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:/Users/fanuca/Documents/spring-angular-app-properties/data-source.properties")
public class DataSourceConfig {

	@Value("${db.url}")
	private String url;
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	@Value("${db.driver-class-name}")
	private String driverClassName;

	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder
				.create()
				.username(this.username)
				.password(this.password)
				.url(this.url)
				.driverClassName(this.driverClassName)
				.build();
	}
	
}
