package com.microideal.SpringSecurityOauth2SsoClient1.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author microideal on 2018/9/18
 */
@EnableOAuth2Sso
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .logout()
            .logoutSuccessUrl("http://localhost:8080/logout")
            .and()
            .antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/login")
            .permitAll()
            .anyRequest()
            .authenticated();
    }
}
