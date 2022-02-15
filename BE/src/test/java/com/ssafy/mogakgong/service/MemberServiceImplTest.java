package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.repository.MemberRepository;
import com.ssafy.mogakgong.request.MemberJoinRequest;
import com.ssafy.mogakgong.request.MemberUpdateRequest;
import com.ssafy.mogakgong.response.MemberResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceImplTest {

    @Autowired
    MemberServiceImpl memberServiceImpl;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;
    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    //@Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 회원가입() throws Exception {
        //given
        MemberJoinRequest member = new MemberJoinRequest();
        member.setEmail("test@daum.net");
        member.setPassword("abc");
        member.setNickname("ssafy");
        member.setImg("noImage");
        member.setBirth("2020-02-02");
        member.setGoal("화팅");
        //when
        String rawPassword = member.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setPassword(encPassword);
        memberServiceImpl.join(member);

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
        memberServiceImpl.join(member1);
        memberServiceImpl.join(member2); // email 중복

        //then
        fail("예외가 발생해야 한다.");

    }

    @Test
    //@Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 회원정보_수정() throws Exception {
        //given
        int memberId = 9;

        //when
        Member member = memberServiceImpl.findOne(memberId);
        List<String> categories = memberServiceImpl.getCategories(memberId);
        MemberResponse memberResponse = memberServiceImpl.getMember(member, categories);

        //then
        System.out.println(categories);
        em.flush();
    }

    @Test
    public void 비밀번호확인_맞음() throws  Exception {
        //given
        String email = "test@naver.com";
        String password = "abc";

        //when
        memberServiceImpl.checkPassword(email, password);

        //then
        em.flush();
    }

    @Test(expected = IllegalStateException.class)
    public void 비밀번호확인_틀림() throws  Exception {
        //given
        String email = "test@naver.com";
        String password = "abcd";

        //when
        memberServiceImpl.checkPassword(email, password);

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
        memberServiceImpl.join(member1);
        memberServiceImpl.join(member2);
        memberServiceImpl.join(member3);
        List<Member> members = memberServiceImpl.findMembers();
        if(members.size()!=3)
            throw new IllegalStateException("조회 실패");

        //then
        em.flush();
    }

    @Test
    public void 회원아이디_반환() throws Exception {
        //given
        String token = "";
        //when
        Member member = memberServiceImpl.tokenToId(token);
        //then
        em.flush();
    }

}