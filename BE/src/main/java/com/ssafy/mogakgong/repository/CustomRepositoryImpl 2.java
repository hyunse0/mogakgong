package com.ssafy.mogakgong.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.mogakgong.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class CustomRepositoryImpl implements CustomRepository {

    private final EntityManager em;
    private final MemberRepository memberRepository;

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
    public List<StudyPlanner> findByRegDate(Integer memberId, String date, String date2) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QStudyPlanner studyPlanner = QStudyPlanner.studyPlanner;
        Member member = memberRepository.findById(memberId).get();
        Timestamp startTime = Timestamp.valueOf(date);
        Timestamp endTime = Timestamp.valueOf(date2);

        return queryFactory
                .select(studyPlanner)
                .from(studyPlanner)
                .where(studyPlanner.regDate.between(startTime,endTime)
                , studyPlanner.isExist.eq(1)
                , studyPlanner.member.eq(member))
                .fetch();

    }

    public List<StudyRoom> findByRecommend(Integer memberId){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QStudyRoom studyRoom = QStudyRoom.studyRoom;
        QStudyRoomCategory studyRoomCategory = QStudyRoomCategory.studyRoomCategory;
        QMemberCategory memberCategory = QMemberCategory.memberCategory;
        QMember member = QMember.member;
        Member findMember = memberRepository.findById(memberId).get();

//        select distinct study_room_id
//        from study_room_category src
//        where src.category_id in (
//        select category_id
//        from member_category mc
//        inner join member m
//        on m.id = mc.member_id
//        where m.id=9);

        return queryFactory
                .selectDistinct(studyRoom)
                .from(studyRoomCategory)
                .where(studyRoomCategory.category.id.in(
                        JPAExpressions
                                .select(memberCategory.id)
                                .from(memberCategory)
                                .innerJoin(member)
                                .on(member.eq(memberCategory.member))
                                .where(member.eq(findMember))
                ))
                .fetch();
    }

}
