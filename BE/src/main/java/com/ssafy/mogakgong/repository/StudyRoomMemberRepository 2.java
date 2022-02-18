package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.StudyRoomMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRoomMemberRepository extends JpaRepository<StudyRoomMember, Integer> {
    StudyRoomMember findByStudyRoomIdAndMemberId(Integer studyRoomId, Integer memberId);
    List<StudyRoomMember> findByMemberId(Integer memberId);
}
