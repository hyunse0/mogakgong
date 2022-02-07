package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.StudyPlanner;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
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
public class StudyPlannerServiceTest {

    @Autowired StudyPlannerService studyPlannerService;
    @Autowired MemberService memberService;
    @Autowired EntityManager em;

    @Test
    //@Rollback(false)
    public void 플래너저장() throws Exception {
        //given
        StudyPlanner studyPlanner = new StudyPlanner();
        studyPlanner.setMember(memberService.findOne(1));
        studyPlanner.setSubject("ssafy");
        studyPlanner.setContent("화이팅");
        studyPlanner.setIsExist(1);

        //when
        studyPlannerService.write(studyPlanner);

        //then
        em.flush();
    }

    @Test
    //@Rollback(false)
    public void 플래너목록() throws Exception {
        //given
        List<StudyPlanner> findStudyPlanners = studyPlannerService.findStudyPlanners(1);

        //when
        for(StudyPlanner now :findStudyPlanners) {
            System.out.println(now.getContent());
        }

        //then
        em.flush();
    }

}