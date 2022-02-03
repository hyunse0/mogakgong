package com.ssafy.mogakgong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // Spring Security 필터가 Spring 필터체인에 등록이 됨
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 이 클래스 자체가 스프링 시큐리티 필터

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/community/**").authenticated() // 커뮤니티는 로그인 해야 접속 가능
                // .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN' or hasRole('ROLE_MANAGER'))")
                // 권한이 이어야 접근 가능
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/member/login");
    }

}
