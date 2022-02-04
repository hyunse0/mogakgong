package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setEmail("test@naver.com");
        member.setPassword("abc");
        member.setNickname("ssafy");
        member.setImg("noImage");
        member.setBirth(Date.valueOf("2020-02-02"));
        member.setGoal("화팅");
        member.setIsExist(1);
        member.setType("mogakgong");
        //when
        memberService.join(member);

        //then
        em.flush();
        assertEquals(member, memberRepository.findById(member.getId()));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원() throws Exception {
        //given
        Member member1 = new Member();
        member1.setEmail("test@ssafy.com");
        member1.setPassword("abc");
        member1.setNickname("ssafy1");
        member1.setIsExist(1);
        member1.setType("mogakgong");

        Member member2 = new Member();
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
    public void 비밀번호확인_맞음() throws Exception {
        //given
        String email = "test@naver.com";
        String password = "abc";

        //when
        memberService.validatePassword(email, password);

        //then
        em.flush();
    }

    @Test(expected = IllegalStateException.class)
    public void 비밀번호확인_틀림() throws Exception {
        //given
        String email = "test@naver.com";
        String password = "abcd";

        //when
        memberService.validatePassword(email, password);

        //then
        fail("예외가 발생해야 한다.");
    }

    @Test
    //@Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 회원정보_수정() throws Exception {
        //given
        Member member1 = new Member();
        member1.setEmail("test@ssafy.com");
        member1.setPassword("abc");
        member1.setNickname("ssafy");
        member1.setIsExist(1);
        member1.setType("mogakgong");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setPassword("bcd");
        member2.setNickname("ssafy2");
        member2.setIsExist(1);
        member2.setType("mogakgong");

        //when
        memberService.update(member1.getId(), member2);

        //then
        em.flush();
    }

    @Test
    //@Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 회원정보_조회() throws Exception {
        //given
        Member member1 = new Member();
        member1.setEmail("test@ssafy.com");
        member1.setPassword("abc");
        member1.setNickname("ssafy");
        member1.setIsExist(1);
        member1.setType("mogakgong");

        Member member2 = new Member();
        member2.setEmail("test@naver.com");
        member2.setPassword("abc");
        member2.setNickname("ssafy");
        member2.setIsExist(1);
        member2.setType("mogakgong");

        Member member3 = new Member();
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
        Member member1 = new Member();
        member1.setEmail("test@ssafy.com");
        member1.setPassword("abc");
        member1.setNickname("ssafy");
        member1.setIsExist(1);
        member1.setType("mogakgong");

        Member member2 = new Member();
        member2.setEmail("test@naver.com");
        member2.setPassword("abc");
        member2.setNickname("ssafy");
        member2.setIsExist(1);
        member2.setType("mogakgong");

        Member member3 = new Member();
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