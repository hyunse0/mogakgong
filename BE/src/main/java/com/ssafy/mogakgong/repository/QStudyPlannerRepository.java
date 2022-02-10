package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.StudyPlanner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QStudyPlannerRepository {
    List<StudyPlanner> findByIsExist();
    List<StudyPlanner> findByRegDate(String date, String date2);
}
