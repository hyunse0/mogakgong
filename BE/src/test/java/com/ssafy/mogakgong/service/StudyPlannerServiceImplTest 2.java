package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.StudyPlanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudyPlannerServiceImplTest {

    @Autowired
    StudyPlannerServiceImpl studyPlannerServiceImpl;
    @Autowired
    MemberServiceImpl memberServiceImpl;
    @Autowired EntityManager em;

    @Test
    //@Rollback(false)
    public void 플래너저장() throws Exception {
        //given
        StudyPlanner studyPlanner = new StudyPlanner();
        studyPlanner.setMember(memberServiceImpl.findOne(1));
        studyPlanner.setSubject("ssafy");
        studyPlanner.setContent("화이팅");
        studyPlanner.setIsExist(1);

        //when
        studyPlannerServiceImpl.write(studyPlanner);

        //then
        em.flush();
    }

    @Test
    //@Rollback(false)
    public void 플래너목록() throws Exception {
        //given
        List<StudyPlanner> findStudyPlanners;
        findStudyPlanners = studyPlannerServiceImpl.findAllStudyPlanners(9, 1);

        //when
        for(StudyPlanner now :findStudyPlanners) {
            System.out.println(now.getContent());
        }

        //then
        em.flush();
    }

    @Test
    @Rollback(false)
    public void 시간_시작() throws Exception {
        //given
        //when
        studyPlannerServiceImpl.startTimer(2);
        //then
        em.flush();
    }

    @Test
    @Rollback(false)
    public void 시간_종료() throws Exception {
        //given
        //when
        studyPlannerServiceImpl.stopTimer(2);
        //then
        em.flush();
    }

}