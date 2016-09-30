package com.foundation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

	@Autowired
	private SecurityAutoConfiguration sa;
	
	public String getToken() {
		return sa.securityProperties().getUser().getPassword();
	}
}
