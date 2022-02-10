package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.StudyPlanner;

import java.util.List;

public interface StudyPlannerService {
    public void write(StudyPlanner studyPlanner);
    public List<StudyPlanner> findStudyPlanners(Integer memberId, Integer isExist);
    public void update(Integer id, StudyPlanner studyPlanner);
    public void delete(Integer id);
}
