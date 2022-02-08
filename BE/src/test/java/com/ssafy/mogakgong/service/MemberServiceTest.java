package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.repository.MemberRepository;
import com.ssafy.mogakgong.request.MemberJoinRequest;
import com.ssafy.mogakgong.request.MemberUpdateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;
    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 회원가입() throws Exception {
        //given
        MemberJoinRequest member = new MemberJoinRequest();
        member.setEmail("test@naver.com");
        member.setPassword("abc");
        member.setNickname("ssafy");
        member.setImg("noImage");
        member.setBirth("2020-02-02");
        member.setGoal("화팅");
        //when
        String rawPassword = member.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setPassword(encPassword);
        memberService.join(member);

        //then
        em.flush();

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원() throws Exception {
        //given
        MemberJoinRequest member1 = new MemberJoinRequest();
        member1.setEmail("test@ssafy.com");
        member1.setPassword("abc");
        member1.setNickname("ssafy1");
        member1.setIsExist(1);
        member1.setType("mogakgong");

        MemberJoinRequest member2 = new MemberJoinRequest();
        member2.setEmail("test@ssafy.com");
        member2.setPassword("bcd");
        member2.setNickname("ssafy2");
        member2.setIsExist(1);
        member2.setType("mogakgong");

        //when
        memberService.join(member1);
        memberService.join(member2); // email 중복

        //then
        fail("예외가 발생해야 한다.");

    }

    @Test
    //@Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 회원정보_수정() throws Exception {
        //given
        MemberJoinRequest member1 = new MemberJoinRequest();
        member1.setEmail("test@naver.com");
        member1.setPassword("abc");
        member1.setNickname("ssafy");
        member1.setIsExist(1);
        member1.setType("mogakgong");
        memberService.join(member1);

        Member member = memberRepository.findByEmail("test@naver.com");

        MemberUpdateRequest member2 = new MemberUpdateRequest();
        member2.setPassword("bcd");
        member2.setNickname("ssafy2");

        //when
        memberService.update(member.getId(), member2);

        //then
        em.flush();
    }

    @Test
    //@Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 회원정보_조회() throws Exception {
        //given
        MemberJoinRequest member1 = new MemberJoinRequest();
        member1.setEmail("test@ssafy.com");
        member1.setPassword("abc");
        member1.setNickname("ssafy");
        member1.setIsExist(1);
        member1.setType("mogakgong");

        MemberJoinRequest member2 = new MemberJoinRequest();
        member2.setEmail("test@naver.com");
        member2.setPassword("abc");
        member2.setNickname("ssafy");
        member2.setIsExist(1);
        member2.setType("mogakgong");

        MemberJoinRequest member3 = new MemberJoinRequest();
        member3.setEmail("test@daum.net");
        member3.setPassword("abc");
        member3.setNickname("ssafy");
        member3.setIsExist(1);
        member3.setType("mogakgong");

        //when
        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);
        List<Member> members = memberService.findMembers();
        if(members.size()!=3)
            throw new IllegalStateException("조회 실패");

        //then
        em.flush();
    }

    @Test
    //@Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 회원정보_삭제() throws Exception {
        //given
        MemberJoinRequest member1 = new MemberJoinRequest();
        member1.setEmail("test@ssafy.com");
        member1.setPassword("abc");
        member1.setNickname("ssafy");
        member1.setIsExist(1);
        member1.setType("mogakgong");

        MemberJoinRequest member2 = new MemberJoinRequest();
        member2.setEmail("test@naver.com");
        member2.setPassword("abc");
        member2.setNickname("ssafy");
        member2.setIsExist(1);
        member2.setType("mogakgong");

        MemberJoinRequest member3 = new MemberJoinRequest();
        member3.setEmail("test@daum.net");
        member3.setPassword("abc");
        member3.setNickname("ssafy");
        member3.setIsExist(1);
        member3.setType("mogakgong");

        //when
        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);
        memberService.delete(member3.getId());
        List<Member> members = memberService.findMembers();
        if(members.size()!=2)
            throw new IllegalStateException("조회 실패");

        //then
        em.flush();
    }

}