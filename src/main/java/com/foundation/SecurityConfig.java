package com.foundation;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		configRequestAuthorization(http);
		configCsrf(http);
		configCors(http);
	}

	private void configRequestAuthorization(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();
	}

	private void configCsrf(HttpSecurity http) throws Exception {
		http.csrf().disable();
	}

	private void configCors(HttpSecurity http) throws Exception {
		http.cors().disable();
	}

}
