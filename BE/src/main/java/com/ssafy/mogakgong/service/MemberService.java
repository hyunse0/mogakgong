package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.config.auth.PrincipalDetails;
import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.repository.MemberRepository;
import com.ssafy.mogakgong.request.JwtRequest;
import com.ssafy.mogakgong.request.MemberJoinRequest;
import com.ssafy.mogakgong.request.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
// 트랜잭션 안에서 데이터 변경이 있을 경우 꼭 필요, 조회가 많은 서비스에는 readOnly 붙이기
// @AllArgsConstructor // 기본적으로 23 ~ 26 소스코드를 생성해 줌
@RequiredArgsConstructor // 기본적으로 final 필드 애들만 23 ~ 26 소스코드 생성
public class MemberService {

    // 변경할 일이 없으므로 final 로 작성하는 걸 추천.
    private final MemberRepository memberRepository;
    // private final AuthenticationManager authenticationManager;

//    @Autowired // 생성자가 하나만 있는 경우 spring 이 자동으로 Autowire 인잭션을 해줌, 작성 안해도 됨
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원가입
    @Transactional // 읽기 전용이면 안 되는 기능이므로 다시 붙임
    public void join(MemberJoinRequest memberJoinRequest) {
        Member member = Member.builder()
                .email(memberJoinRequest.getEmail())
                .password(memberJoinRequest.getPassword())
                .nickname(memberJoinRequest.getNickname())
                .img(memberJoinRequest.getImg())
                .birth(memberJoinRequest.getBirth())
                .goal(memberJoinRequest.getGoal())
                .isExist(memberJoinRequest.getIsExist())
                .type(memberJoinRequest.getType())
                .build();
        validateDuplicateMember(member); // 이메일 중복 검사
        memberRepository.save(member);
    }

    //로그인
//    @Transactional
//    public String login(JwtRequest request) throws Exception {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication((authentication));
//
//        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//        return principalDetails.getUsername();
//    }


    // 이메일 중복 검사
    public void validateDuplicateMember(Member member) {
        // 해당 E-mail 을 사용중인 멤버 탐색
        Member findMember = memberRepository.findByEmail(member.getEmail());
        // 사용중인 멤버가 존재한다면
        if ( findMember != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            //return true; // E-mail 중복 사용 불가능
        }
        //return false; // E-mail 사용 가능
    }

    // 회원가입 시 비밀번호 확인
    public Boolean checkPassword(String password, String passwordCheck) {
        if(password.equals(passwordCheck)){
            return true; // 비밀번호 같음
        }
        return false; // 비밀번호 다름
    }

    // 로그인 및 마이페이지 입장 시 비밀번호 확인
    public void validatePassword(String email, String password) {
        // 해당 E-mail 을 사용중인 멤버 탐색
        Member findMember = memberRepository.findByEmail(email);
        if(!findMember.getPassword().equals(password)) {
            throw new IllegalStateException("비밀번호 일치하지 않습니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findByIsExist(1);
    }

    // 회원 한 명 조회
    public Member findOne(int memberId) {
        return memberRepository.findById(memberId);
    }

    // 회원 정보 수정
    @Transactional
    public void update(int id, MemberUpdateRequest memberUpdateRequest) {
        Member prevMember = memberRepository.findById(id);
        prevMember.setBirth(memberUpdateRequest.getBirth());
        prevMember.setNickname(memberUpdateRequest.getNickname());
        prevMember.setPassword(memberUpdateRequest.getPassword());
        prevMember.setGoal(memberUpdateRequest.getGoal());
        prevMember.setImg(memberUpdateRequest.getImg());
    }

    // 회원 정보 삭제
    @Transactional
    public void delete(int id) {
        Member member = memberRepository.findById(id);
        member.setIsExist(0);
    }
}
