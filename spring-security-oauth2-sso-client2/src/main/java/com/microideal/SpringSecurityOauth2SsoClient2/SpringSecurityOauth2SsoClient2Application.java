package com.microideal.SpringSecurityOauth2SsoClient2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class SpringSecurityOauth2SsoClient2Application extends SpringBootServletInitializer {

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityOauth2SsoClient2Application.class, args);
	}
}
