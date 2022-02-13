package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.StudyPlanner;
import com.ssafy.mogakgong.domain.StudyPlannerTimer;
import com.ssafy.mogakgong.repository.CustomStudyPlannerRepositoryImpl;
import com.ssafy.mogakgong.repository.StudyPlannerRepository;
import com.ssafy.mogakgong.repository.StudyPlannerTimerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyPlannerServiceImpl implements StudyPlannerService{

    private final StudyPlannerRepository studyPlannerRepository;
    private final StudyPlannerTimerRepository studyPlannerTimerRepository;
    private final CustomStudyPlannerRepositoryImpl customStudyPlannerRepository;

    // 스터디 플래너 작성
    @Transactional // 읽기 전용이면 안 되는 기능이므로 다시 붙임
    public void write(StudyPlanner studyPlanner) {
        studyPlannerRepository.save(studyPlanner);
    }

    // 스터디 플래너 전체 목록
    public List<StudyPlanner> findAllStudyPlanners(Integer memberId, Integer isExist) {

        return studyPlannerRepository.findByMemberIdAndIsExist(memberId, isExist);
    }

    // 스터디 플래너 날짜별 목록
    public List<StudyPlanner> findByDateStudyPlanners(Integer memberId, String startDate, String endDate) {

        return customStudyPlannerRepository.findByRegDate(memberId, startDate, endDate);
    }

    // 스터디 플래너 수정
    @Transactional
    public void update(Integer id, StudyPlanner studyPlanner) {
        Optional<StudyPlanner> studyPlannerOptional = studyPlannerRepository.findById(id);
        StudyPlanner prevStudyPlanner = studyPlannerOptional.get();
        prevStudyPlanner.setSubject(studyPlanner.getSubject());
        prevStudyPlanner.setContent(studyPlanner.getContent());
    }

    // 스터디 플래너 삭제
    @Transactional
    public void delete(Integer id) {
        Optional<StudyPlanner> studyPlannerOptional = studyPlannerRepository.findById(id);
        StudyPlanner prevStudyPlanner = studyPlannerOptional.get();
        prevStudyPlanner.setIsExist(0);
    }

    // 스터디 플래너 타이머 시작
    @Transactional
    public void startTimer(Integer studyPlannerId) {
        StudyPlannerTimer studyPlannerTimer = new StudyPlannerTimer();
        StudyPlanner studyPlanner = studyPlannerRepository.findById(studyPlannerId).get();
        studyPlannerTimer.setStudyPlanner(studyPlanner);
        studyPlannerTimerRepository.save(studyPlannerTimer);
    }

    // 스터디 플래너 타이머 종료
    @Transactional
    public void stopTimer(Integer studyPlannerId) {
        StudyPlannerTimer studyPlannerTimer = studyPlannerTimerRepository.findFirstByStudyPlannerIdOrderByIdDesc(studyPlannerId);
        //Timestamp date = new Timestamp(new Date().getTime());
        Long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        studyPlannerTimer.setEndTime(Timestamp.valueOf(sdf.format(now)));
        System.out.println(sdf.format(now));
    }
}
