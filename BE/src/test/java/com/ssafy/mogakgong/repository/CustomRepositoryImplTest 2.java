package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.Category;
import com.ssafy.mogakgong.domain.StudyPlanner;
import com.ssafy.mogakgong.domain.StudyRoom;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomRepositoryImplTest {

    @Autowired
    CustomRepositoryImpl customRepositoryImpl;
    @Autowired
    EntityManager em;

    @Test
    public void 스터디플래너_보기() throws Exception {
        //given

        //when
        List<StudyPlanner> studyPlannerList = customRepositoryImpl.findByIsExist();
        for(StudyPlanner s : studyPlannerList){
            System.out.println(s.getSubject());
        }
        //then
        em.flush();
    }

    @Test
    public void 스터디플래너_날짜검색() throws Exception {
        //given
        String start = "2022-02-09 00:00:00";
        String end = "2022-02-10 00:00:00";
        //when
        List<StudyPlanner> studyPlannerList = customRepositoryImpl.findByRegDate(9, start, end);

        for(StudyPlanner s : studyPlannerList){
            System.out.println(s.getSubject());
        }

        //then
        em.flush();
    }

    @Test
    public void 추천_스터디룸_검색() throws Exception {
        //given
        Integer memberId = 9;

        //when
        List<StudyRoom> studyRooms = customRepositoryImpl.findByRecommend(memberId);
        for(StudyRoom s : studyRooms){
            System.out.println(s.getId());
        }
        //then
        em.flush();
    }

}