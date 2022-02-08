package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.Community;
import com.ssafy.mogakgong.domain.StudyRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRoomRepository extends JpaRepository<StudyRoom, Integer> {
    Page<Community> findByIsExistOrderByIdDesc(Integer isExist, Pageable pageable);
}
