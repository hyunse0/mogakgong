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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.spi.service.contexts.SecurityContext;

import javax.persistence.Basic;

@Configuration
@EnableWebSecurity // Spring Security 필터가 Spring 필터체인에 등록이 됨
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 이 클래스 자체가 스프링 시큐리티 필터

    private final CorsFilter corsFilter;
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
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository))
                .authorizeRequests()
                //.antMatchers("/community/**").authenticated()
                .antMatchers("/community/**")
                .access("hasRole('ROLE_USER')")
                .anyRequest().permitAll();
//        http.authorizeRequests()
//                .antMatchers("/community/**").authenticated() // 커뮤니티는 로그인 해야 접속 가능
//                // .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN' or hasRole('ROLE_MANAGER'))")
//                // 권한이 이어야 접근 가능
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("") // 로그인 폼 파일 이름 넣어야함
//                .usernameParameter("email") // 파라미터 username 에서 email 로 바꾸려면 여기서 설정
//                .loginProcessingUrl("/member/login")
//                .defaultSuccessUrl("/"); // 메인 페이지 주소로 채워놓기
    }

}
