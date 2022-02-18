package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.config.auth.PrincipalDetails;
import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login")
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 시큐리티 session => Authentication => UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 파라미터 변수 String email 이랑 프론트에서 <input type="text" name ="email"> 부분 맞춰야 매핑 됨

        Member memberEntity = memberRepository.findByEmail(username);
        System.out.println("memberEntity:" + memberEntity);
        if(memberEntity != null) {
            return new PrincipalDetails(memberEntity); // 시큐리티 세션( authentication( UserDetails ) )
        }
        return null;
    }
}
