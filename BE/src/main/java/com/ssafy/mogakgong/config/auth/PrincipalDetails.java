package com.ssafy.mogakgong.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킴
// 로그인을 진행이 완료가 되면 시큐리티 session 을 만들어 준다. (Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 Member 정보가 있어야 됨.
// Member 오브젝트 타입 => UserDetails 타입 객체

// Security Session => Authentication 객체 => UserDetails 타입

import com.ssafy.mogakgong.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PrincipalDetails implements UserDetails{

    private Member member;

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    // 해당 Member 의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Member 권한 없음
        return null;
    }

    @Override
    public String getPassword() {

        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
