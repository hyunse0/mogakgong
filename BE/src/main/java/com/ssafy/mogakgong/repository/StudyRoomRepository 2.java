package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.StudyRoom;
import com.ssafy.mogakgong.domain.StudyRoomMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRoomRepository extends JpaRepository<StudyRoom, Integer> {
    Page<StudyRoom> findByIsExistOrderByIdDesc(Integer isExist, Pageable pageable);

}
