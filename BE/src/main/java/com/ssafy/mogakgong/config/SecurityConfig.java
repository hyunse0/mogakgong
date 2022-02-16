package com.ssafy.mogakgong.config;

import com.ssafy.mogakgong.jwt.JwtAuthenticationFilter;
import com.ssafy.mogakgong.jwt.JwtAuthorizationFilter;
import com.ssafy.mogakgong.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity // Spring Security 필터가 Spring 필터체인에 등록이 됨
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 이 클래스 자체가 스프링 시큐리티 필터

    //private final CorsFilter corsFilter;
    private final MemberRepository memberRepository;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.addFilterBefore(new LoginFilter(), SecurityContextPersistenceFilter.class); // 필터 실행 시간 설정
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 세션 사용하지 않겠다.
                .and()
                //.addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository))
                .authorizeRequests()
                //.authenticated()
                .antMatchers("/api/v2/**", "/swagger-ui.html", "/swagger/**",
                        "/swagger-resources/**", "/webjars/**", "/v2/api-docs")
                .permitAll()
                .antMatchers("/studyroom", "GET", "/community/**", "GET", "/member/join", "/login", "/")
                .permitAll()
                .anyRequest().access("hasRole('ROLE_USER')");
    }

}
