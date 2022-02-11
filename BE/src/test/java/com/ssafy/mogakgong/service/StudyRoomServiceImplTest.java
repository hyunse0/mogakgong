package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyRoom;
import com.ssafy.mogakgong.domain.StudyRoomHashtag;
import com.ssafy.mogakgong.repository.MemberRepository;
import com.ssafy.mogakgong.repository.StudyRoomRepository;
import com.ssafy.mogakgong.request.StudyRoomRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudyRoomServiceImplTest {

    @Autowired
    private StudyRoomService studyRoomService;
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
        studyRoomRequest.setTitle("모각코방");
        studyRoomRequest.setPassword("1234");
        studyRoomRequest.setDescription("모여서 각자 코딩하는 방");
        studyRoomRequest.setStartDate("2022-02-09 00:00:00");
        studyRoomRequest.setEndDate("2022-02-10 00:00:00");
        studyRoomRequest.setLimit_people(4);
        studyRoomRequest.setImg("noImg");
        studyRoomRequest.setGoalTime(6);
        studyRoomRequest.setUrl("www.url.com");
        studyRoomRequest.setHashtag("코딩,자유롭게,물흐리기 금지");

        Member member = memberRepository.findById(9).get();

        //when
        studyRoomService.create(studyRoomRequest, member);

        //then
        em.flush();
    }

    @Test
    public void 스터디룸_해시태그출력() throws Exception {
        //given
        StudyRoom studyRoom = studyRoomRepository.findById(7).get();

        //when
        for(StudyRoomHashtag s : studyRoom.getStudyRoomHashtags()){
            System.out.println(s.getName());
        }

        //then
        em.flush();
    }

}