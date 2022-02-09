package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.request.StudyRoomRequest;
import com.ssafy.mogakgong.request.StudyRoomUpdateRequest;
import com.ssafy.mogakgong.response.CommunityResponse;
import com.ssafy.mogakgong.response.StudyRoomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyRoomService {
    public void create(StudyRoomRequest studyRoomRequest, Member member);
    public Page<CommunityResponse> getStudyRoomList(Pageable pageable);
    public StudyRoomResponse getStudyRoom(Integer studyRoomId, Pageable pageable);
    public void updateStudyRoom(Integer id, StudyRoomUpdateRequest studyRoomUpdateRequest);
    public void deleteStudyRoom(Integer id);
}
