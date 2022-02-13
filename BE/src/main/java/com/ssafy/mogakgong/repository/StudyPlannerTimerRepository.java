package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.StudyPlanner;
import com.ssafy.mogakgong.domain.StudyPlannerTimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyPlannerTimerRepository extends JpaRepository<StudyPlannerTimer, Integer> {
    public StudyPlannerTimer findFirstByStudyPlannerIdOrderByIdDesc(Integer studyPlannerId);

}
