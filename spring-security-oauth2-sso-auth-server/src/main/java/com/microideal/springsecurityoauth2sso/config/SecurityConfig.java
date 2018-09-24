package com.microideal.springsecurityoauth2sso.config;

import com.microideal.springsecurityoauth2sso.userdetailservice.DomainUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author microideal on 2018/9/18
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new DomainUserDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭跨站请求伪造。因为框架内部防止CSRF（Cross-site request forgery跨站请求伪造）的发生，限制了除了get以外的大多数方法，如果POST请求会403。最好的做法是在页面配置csrf。
        //http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/oauth/**","/login**", "/logout").permitAll()
                .anyRequest().authenticated()   // 其他地址的访问均需验证权限
                .and()
                .formLogin()
                .loginPage("/login")
                /*.and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")*/;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
