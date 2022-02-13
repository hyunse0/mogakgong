package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyRoom;
import com.ssafy.mogakgong.domain.StudyRoomHashtag;
import com.ssafy.mogakgong.repository.MemberRepository;
import com.ssafy.mogakgong.repository.StudyRoomRepository;
import com.ssafy.mogakgong.request.StudyRoomRequest;
import com.ssafy.mogakgong.request.StudyRoomUpdateRequest;
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
    //@Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 스터디룸_등록() throws Exception {
        //given
        StudyRoomRequest studyRoomRequest = new StudyRoomRequest();
        studyRoomRequest.setTitle("모각코방");
        studyRoomRequest.setPassword("1234");
        studyRoomRequest.setDescription("모여서 각자 코딩하는 방");
        studyRoomRequest.setStartDate("2022-02-09 00:00:00");
        studyRoomRequest.setEndDate("2022-02-10 00:00:00");
        studyRoomRequest.setLimit_people(4);
        studyRoomRequest.setImg("noImg");
        studyRoomRequest.setGoalTime(6);
        studyRoomRequest.setUrl("www.url.com");

        studyRoomRequest.getStudyRoomHashtags().add("코딩");
        studyRoomRequest.getStudyRoomHashtags().add("자유롭게");
        studyRoomRequest.getStudyRoomHashtags().add("물흐리기 금지");

        Member member = memberRepository.findById(9).get();

        //when
        studyRoomServiceImpl.create(studyRoomRequest, member);
        StudyRoom studyRoom = studyRoomServiceImpl.findStudyRoomByUrl(studyRoomRequest.getUrl());
        studyRoomServiceImpl.enter(studyRoom.getId(), member.getId(),2);

        //then
        em.flush();
    }

    @Test
    public void 스터디룸_해시태그출력() throws Exception {
        //given
        StudyRoom studyRoom = studyRoomRepository.findById(9).get();

        //when
        for(StudyRoomHashtag s : studyRoom.getStudyRoomHashtags()){
            System.out.println(s.getName());
        }

        //then
        em.flush();
    }

    @Test
    //@Rollback(false) // test 내용 자동으로 롤백을 하는데 보고 싶다면 설정
    public void 스터디룸_수정() throws Exception {
        //given
        StudyRoomUpdateRequest studyRoomUpdateRequest = new StudyRoomUpdateRequest();
        studyRoomUpdateRequest.setTitle("모각코방");
        studyRoomUpdateRequest.setPassword("1234");
        studyRoomUpdateRequest.setDescription("모여서 각자 코딩하는 방");
        studyRoomUpdateRequest.setStartDate("2022-02-09 00:00:00");
        studyRoomUpdateRequest.setEndDate("2022-02-10 00:00:00");
        studyRoomUpdateRequest.setLimitPeople(4);
        studyRoomUpdateRequest.setImg("noImg");
        studyRoomUpdateRequest.setGoalTime(6);
        studyRoomUpdateRequest.setUrl("www.com");

        studyRoomUpdateRequest.getStudyRoomHashtags().add("수학");
        studyRoomUpdateRequest.getStudyRoomHashtags().add("과학");
        studyRoomUpdateRequest.getStudyRoomHashtags().add("이공계");

        //when
        studyRoomServiceImpl.updateStudyRoom(9, studyRoomUpdateRequest);

        //then
        em.flush();
    }

    @Test
    //@Rollback(false)
    public void 스터디룸_입장() throws  Exception {
        //given
        //when
        studyRoomServiceImpl.enter(11,9,0);
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