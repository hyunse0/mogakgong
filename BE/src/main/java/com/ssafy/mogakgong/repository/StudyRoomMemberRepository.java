package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.StudyRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRoomMemberRepository extends JpaRepository<StudyRoomMember, Integer> {
    StudyRoomMember findByStudyRoomIdAndMemberId(Integer studyRoomId, Integer memberId);
    StudyRoomMember findByMemberId(Integer memberId);

}
