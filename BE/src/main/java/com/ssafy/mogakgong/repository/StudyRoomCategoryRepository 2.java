package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.MemberCategory;
import com.ssafy.mogakgong.domain.StudyRoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRoomCategoryRepository extends JpaRepository<StudyRoomCategory, Integer> {
    List<StudyRoomCategory> findByStudyRoomId(Integer studyRoomId);
    void deleteAllByStudyRoomId(Integer studyRoomId);
}
