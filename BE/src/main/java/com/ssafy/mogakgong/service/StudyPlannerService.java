package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyPlanner;
import com.ssafy.mogakgong.repository.StudyPlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyPlannerService {

    private final StudyPlannerRepository studyPlannerRepository;
    private Integer isExist = 1;

    // 스터디 플래너 작성
    @Transactional // 읽기 전용이면 안 되는 기능이므로 다시 붙임
    public void write(StudyPlanner studyPlanner) {
        studyPlannerRepository.save(studyPlanner);
    }

    // 스터디 플래너 목록
    public List<StudyPlanner> findStudyPlanners(Integer memberId, Integer isExist) { return studyPlannerRepository.findByMemberIdAndIsExist(memberId, isExist);}

    // 스터디 플래너 수정
    @Transactional
    public void update(int id, StudyPlanner studyPlanner) {
        StudyPlanner prevStudyPlanner = studyPlannerRepository.findById(id);
        prevStudyPlanner.setSubject(studyPlanner.getSubject());
        prevStudyPlanner.setContent(studyPlanner.getContent());
    }

    // 스터디 플래너 삭제
    @Transactional
    public void delete(int id) {
        StudyPlanner prevStudyPlanner = studyPlannerRepository.findById(id);
        prevStudyPlanner.setIsExist(0);
    }


}
