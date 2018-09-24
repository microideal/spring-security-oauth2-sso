package com.microideal.springsecurityoauth2sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
public class SpringSecurityOauth2SsoAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityOauth2SsoAuthApplication.class, args);
	}
}
