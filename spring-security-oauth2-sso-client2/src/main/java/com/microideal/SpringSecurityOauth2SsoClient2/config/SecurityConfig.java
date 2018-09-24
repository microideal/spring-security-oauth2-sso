package com.microideal.SpringSecurityOauth2SsoClient2.config;

/**
 * @author microideal on 2018/9/18
 */
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableOAuth2Sso
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.logout()
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated();
    }

}
