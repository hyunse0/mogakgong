package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyPlanner;
import com.ssafy.mogakgong.repository.StudyPlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyPlannerServiceImpl implements StudyPlannerService{

    private final StudyPlannerRepository studyPlannerRepository;

    // 스터디 플래너 작성
    @Transactional // 읽기 전용이면 안 되는 기능이므로 다시 붙임
    public void write(StudyPlanner studyPlanner) {
        studyPlannerRepository.save(studyPlanner);
    }

    // 스터디 플래너 목록
    public List<StudyPlanner> findStudyPlanners(Integer memberId, Integer isExist) { return studyPlannerRepository.findByMemberIdAndIsExist(memberId, isExist);}

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


}
