package com.ssafy.mogakgong.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.mogakgong.domain.QStudyPlanner;
import com.ssafy.mogakgong.domain.StudyPlanner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

@Repository
@AllArgsConstructor
public class CustomStudyPlannerRepositoryImpl implements CustomStudyPlannerRepository {

    private final EntityManager em;

    // queryDSL 테스트용
    public List<StudyPlanner> findByIsExist() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QStudyPlanner studyPlanner = QStudyPlanner.studyPlanner;

        return queryFactory
                .select(studyPlanner)
                .from(studyPlanner)
                .where(studyPlanner.isExist.eq(1))
                .fetch();
    }

    // 날짜 검색, 시작 날짜와 끝 날짜 String 으로 주면 그 사이 StudyPlanner 검색
    public List<StudyPlanner> findByRegDate(String date, String date2) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QStudyPlanner studyPlanner = QStudyPlanner.studyPlanner;

        Timestamp startTime = Timestamp.valueOf(date);
        Timestamp endTime = Timestamp.valueOf(date2);

        return queryFactory
                .select(studyPlanner)
                .from(studyPlanner)
                .where(studyPlanner.regDate.between(startTime,endTime)
                , studyPlanner.isExist.eq(1))
                .fetch();

    }


}
