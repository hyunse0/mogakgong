package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.StudyPlanner;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomStudyPlannerRepository {
    List<StudyPlanner> findByIsExist();
    List<StudyPlanner> findByRegDate(Integer memberId, String date, String date2);

}
