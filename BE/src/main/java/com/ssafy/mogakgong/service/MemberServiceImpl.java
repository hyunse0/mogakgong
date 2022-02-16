package com.ssafy.mogakgong.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.mogakgong.domain.*;
import com.ssafy.mogakgong.repository.CategoryRepository;
import com.ssafy.mogakgong.repository.MemberCategoryRepository;
import com.ssafy.mogakgong.repository.MemberRepository;
import com.ssafy.mogakgong.request.MemberJoinRequest;
import com.ssafy.mogakgong.request.MemberUpdateRequest;
import com.ssafy.mogakgong.response.MemberResponse;
import com.ssafy.mogakgong.response.StudyRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
// 트랜잭션 안에서 데이터 변경이 있을 경우 꼭 필요, 조회가 많은 서비스에는 readOnly 붙이기
// @AllArgsConstructor // 기본적으로 23 ~ 26 소스코드를 생성해 줌
@RequiredArgsConstructor // 기본적으로 final 필드 애들만 23 ~ 26 소스코드 생성
public class MemberServiceImpl implements MemberService {

    // 변경할 일이 없으므로 final 로 작성하는 걸 추천.
    private final MemberRepository memberRepository;
    private final MemberCategoryRepository memberCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
                .birth(Date.valueOf(memberJoinRequest.getBirth()))
                .goal(memberJoinRequest.getGoal())
                .isExist(memberJoinRequest.getIsExist())
                .type(memberJoinRequest.getType())
                .build();
        validateDuplicateMember(member); // 이메일 중복 검사
        memberRepository.save(member);
    }

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
    public Boolean validatePassword(String password, String passwordCheck) {
        if(password.equals(passwordCheck)){
            return true; // 비밀번호 같음
        }
        return false; // 비밀번호 다름
    }


    // 로그인 및 마이페이지 입장 시 비밀번호 확인
    public void checkPassword(String email, String password) {
        // 해당 E-mail 을 사용중인 멤버 탐색
        Member findMember = memberRepository.findByEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(password, findMember.getPassword())) {
            throw new IllegalStateException("비밀번호 일치하지 않습니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findByIsExist(1);
    }

    // 회원 한 명 조회
    public Member findOne(Integer memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.get();
        return member;
    }

    public List<String> getCategories(Integer memberId) {
        List<String> categories = new ArrayList<>();
        List<MemberCategory> memberCategories = memberCategoryRepository.findByMemberId(memberId);
        if(memberCategories != null) {
            for(MemberCategory memberCategory : memberCategories) {
                Category findCategory = categoryRepository.findById(memberCategory.getId()).get();
                categories.add(findCategory.getName());
            }
        }
        return categories;
    }

    public MemberResponse getMember(Member member, List<String> categories) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .img(member.getImg())
                .birth(member.getBirth())
                .goal(member.getGoal())
                .category(categories)
                .build();
    }

    // 회원 정보 수정
    @Transactional
    public void update(Integer id, MemberUpdateRequest memberUpdateRequest) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        Member prevMember = memberOptional.get();
        prevMember.setBirth(memberUpdateRequest.getBirth());
        prevMember.setNickname(memberUpdateRequest.getNickname());
        prevMember.setGoal(memberUpdateRequest.getGoal());
        prevMember.setImg(memberUpdateRequest.getImg());
    }

    // 회원 정보 카테고리 추가
    @Transactional
    public void updateCategory(Integer memberId, List<String> memberCategories) {
        memberCategoryRepository.deleteAllByMemberId(memberId); // 기존 카테고리 삭제
        for (String category : memberCategories) {
            Category findCategory = categoryRepository.findFirstByName(category);
            if(findCategory == null) {
                System.out.println("카테고리 이름 틀림");
                continue;
            }
            MemberCategory memberCategory = new MemberCategory();
            memberCategory.setMember(memberRepository.findById(memberId).get());
            memberCategory.setCategory(findCategory);
            memberCategoryRepository.save(memberCategory); // 새로운 카테고리 추가
        }
    }

    // 회원 정보 삭제
    @Transactional
    public void delete(Integer id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        Member member = memberOptional.get();
        member.setIsExist(0);
        memberCategoryRepository.deleteAllByMemberId(id);
    }

    public Member tokenToId(String jwtToken) {
        String changeJwtToken = jwtToken.replace("Bearer ", "");
        String username = JWT.require(Algorithm.HMAC512("cos")).build().verify(changeJwtToken).getClaim("username").asString();
        Member member = memberRepository.findByEmail(username);
        return member;
    }
}
