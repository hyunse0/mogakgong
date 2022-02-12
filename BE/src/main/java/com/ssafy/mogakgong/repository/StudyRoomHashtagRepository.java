package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.StudyRoomHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRoomHashtagRepository extends JpaRepository<StudyRoomHashtag, Integer> {
    public List<StudyRoomHashtag> findByStudyRoomId(Integer studyRoomId);
    public void deleteAllByStudyRoomId(Integer studyRoomId);
}
