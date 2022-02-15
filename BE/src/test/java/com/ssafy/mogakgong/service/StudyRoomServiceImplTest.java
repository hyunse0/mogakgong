package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyRoom;
import com.ssafy.mogakgong.domain.StudyRoomHashtag;
import com.ssafy.mogakgong.repository.MemberRepository;
import com.ssafy.mogakgong.repository.StudyRoomRepository;
import com.ssafy.mogakgong.request.StudyRoomRequest;
import com.ssafy.mogakgong.request.StudyRoomUpdateRequest;
import com.ssafy.mogakgong.response.StudyRoomResponse;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudyRoomServiceImplTest {

    @Autowired
    private StudyRoomServiceImpl studyRoomServiceImpl;
    @Autowired
    private StudyRoomRepository studyRoomRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 스터디룸_등록() throws Exception {
        //given
        StudyRoomRequest studyRoomRequest = new StudyRoomRequest();
        studyRoomRequest.setTitle("공무원방");
        studyRoomRequest.setPassword("1234");
        //studyRoomRequest.setDescription("모여서 각자 코딩하는 방");
        studyRoomRequest.setStartDate("2022-02-09 00:00:00");
        studyRoomRequest.setEndDate("2022-02-10 00:00:00");
        studyRoomRequest.setLimit_people(4);
        //studyRoomRequest.setImg("noImg");
        studyRoomRequest.setGoalTime(6);
        studyRoomRequest.setUrl("www.url2.com");

        studyRoomRequest.getStudyRoomHashtags().add("조용");
        studyRoomRequest.getStudyRoomHashtags().add("시간엄수");
        //studyRoomRequest.getStudyRoomHashtags().add("인공지능");
        studyRoomRequest.getStudyRoomCategories().add("고시");
        studyRoomRequest.getStudyRoomCategories().add("자격증");

        Member member = memberRepository.findById(18).get();

        //when
        Integer id = studyRoomServiceImpl.create(studyRoomRequest, member);
        studyRoomServiceImpl.enter(id, member.getId(),2);

        //then
        em.flush();
    }

    @Test
    @Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 스터디룸_수정() throws Exception {
        //given
        StudyRoomUpdateRequest studyRoomUpdateRequest = new StudyRoomUpdateRequest();
        studyRoomUpdateRequest.setTitle("모각코방");
        studyRoomUpdateRequest.setPassword("1234");
        studyRoomUpdateRequest.setDescription("모여서 각자 코딩하는 방");
        studyRoomUpdateRequest.setStartDate("2022-02-09 00:00:00");
        studyRoomUpdateRequest.setEndDate("2022-02-10 00:00:00");
        studyRoomUpdateRequest.setLimitPeople(4);
        studyRoomUpdateRequest.setGoalTime(6);
        studyRoomUpdateRequest.setUrl("www.com");

        studyRoomUpdateRequest.getStudyRoomHashtags().add("수학");
        studyRoomUpdateRequest.getStudyRoomHashtags().add("과학");
        studyRoomUpdateRequest.getStudyRoomHashtags().add("이공계");
        studyRoomUpdateRequest.getStudyRoomCategories().add("취업");
        studyRoomUpdateRequest.getStudyRoomCategories().add("자기개발");

        //when
        studyRoomServiceImpl.updateStudyRoom(14, studyRoomUpdateRequest);

        //then
        em.flush();
    }

    @Test
    @Rollback(false)
    public void 스터디룸_입장() throws  Exception {
        //given
        //when
        studyRoomServiceImpl.enter(12,9,0);
        //then
        em.flush();
    }

    @Test
    //@Rollback(false)
    public void 스터디룸_권한변경() throws  Exception {
        //given
        //when
        studyRoomServiceImpl.changeAuthority(11,9,1);
        //then
        em.flush();
    }
}