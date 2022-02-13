package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyRoom;
import com.ssafy.mogakgong.request.StudyRoomRequest;
import com.ssafy.mogakgong.request.StudyRoomUpdateRequest;
import com.ssafy.mogakgong.response.CommunityResponse;
import com.ssafy.mogakgong.response.StudyRoomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyRoomService {
    public void create(StudyRoomRequest studyRoomRequest, Member member);
    public void validateDuplicateStudyRoom(StudyRoom studyRoom);
    public StudyRoom findStudyRoomByUrl(String url);
    public Page<StudyRoomResponse> getStudyRoomList(Pageable pageable);
    public StudyRoomResponse getStudyRoom(Integer studyRoomId, Pageable pageable);
    public void updateStudyRoom(Integer id, StudyRoomUpdateRequest studyRoomUpdateRequest);
    public void deleteStudyRoom(Integer id);
    public void enter(Integer studyRoomId, Integer memberId, Integer level);
    public void changeAuthority(Integer studyRoomId, Integer memberId, Integer level);
    public void exit(Integer studyRoomId, Integer memberId);
}
