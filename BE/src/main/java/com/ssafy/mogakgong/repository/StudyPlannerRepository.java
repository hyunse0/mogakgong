package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.StudyPlanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyPlannerRepository extends JpaRepository<StudyPlanner, Integer> {
    public List<StudyPlanner> findByMemberIdAndIsExist(Integer memberId, Integer isExist);
    public StudyPlanner findById(int id);
}
