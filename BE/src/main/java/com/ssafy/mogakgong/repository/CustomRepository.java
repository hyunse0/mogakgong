package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.StudyPlanner;
import com.ssafy.mogakgong.domain.StudyRoom;

import java.util.List;

public interface CustomRepository {
    List<StudyPlanner> findByIsExist();
    List<StudyPlanner> findByRegDate(Integer memberId, String date, String date2);
    List<StudyRoom> findByRecommend(Integer memberId);
}
