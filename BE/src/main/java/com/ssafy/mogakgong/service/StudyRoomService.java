package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Community;
import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyRoom;
import com.ssafy.mogakgong.repository.StudyRoomMemberRepository;
import com.ssafy.mogakgong.repository.StudyRoomRepository;
import com.ssafy.mogakgong.request.MemberUpdateRequest;
import com.ssafy.mogakgong.request.StudyRoomRequest;
import com.ssafy.mogakgong.request.StudyRoomUpdateRequest;
import com.ssafy.mogakgong.response.CommentResponse;
import com.ssafy.mogakgong.response.CommunityResponse;
import com.ssafy.mogakgong.response.StudyRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyRoomService {

    private final StudyRoomRepository studyRoomRepository;
    private final StudyRoomMemberRepository studyRoomMemberRepository;
    private Integer exist = 1;

    @Transactional
    public void create(StudyRoomRequest studyRoomRequest, Member member) {
        StudyRoom studyRoom = StudyRoom.builder()
                .title(studyRoomRequest.getTitle())
                .password(studyRoomRequest.getPassword())
                .description(studyRoomRequest.getDescription())
                .startDate(Timestamp.valueOf(studyRoomRequest.getStartDate()))
                .endDate(Timestamp.valueOf(studyRoomRequest.getEndDate()))
                .limit(studyRoomRequest.getLimit())
                .img(studyRoomRequest.getImg())
                .goalTime(studyRoomRequest.getGoalTime())
                .url(studyRoomRequest.getUrl())
                .isExist(exist)
                .member(member)
                .build();
        studyRoomRepository.save(studyRoom);
    }

    public Page<CommunityResponse> getStudyRoomList(Pageable pageable) {
        return studyRoomRepository.findByIsExistOrderByIdDesc(1, pageable)
                .map(CommunityResponse::from);
    }

    public StudyRoomResponse getStudyRoom(Integer studyRoomId, Pageable pageable) {
        StudyRoom studyRoom = studyRoomRepository.findById(studyRoomId).get();

        return StudyRoomResponse.builder()
                .id(studyRoom.getId())
                .title(studyRoom.getTitle())
                .password(studyRoom.getPassword())
                .description(studyRoom.getDescription())
                .startDate(studyRoom.getStartDate())
                .endDate(studyRoom.getEndDate())
                .limit(studyRoom.getLimit())
                .img(studyRoom.getImg())
                .goalTime(studyRoom.getGoalTime())
                .url(studyRoom.getUrl())
                .member(studyRoom.getMember())
                .build();
    }

    @Transactional
    public void updateStudyRoom(Integer id, StudyRoomUpdateRequest studyRoomUpdateRequest) {
        Optional<StudyRoom> studyRoomOptional = studyRoomRepository.findById(id);
        StudyRoom prevStudyRoom = studyRoomOptional.get();
        prevStudyRoom.setTitle(studyRoomUpdateRequest.getTitle());
        prevStudyRoom.setPassword(studyRoomUpdateRequest.getPassword());
        prevStudyRoom.setDescription(studyRoomUpdateRequest.getDescription());
        prevStudyRoom.setStartDate(Timestamp.valueOf(studyRoomUpdateRequest.getStartDate()));
        prevStudyRoom.setEndDate(Timestamp.valueOf(studyRoomUpdateRequest.getEndDate()));
        prevStudyRoom.setLimit(studyRoomUpdateRequest.getLimit());
        prevStudyRoom.setImg(studyRoomUpdateRequest.getImg());
        prevStudyRoom.setGoalTime(studyRoomUpdateRequest.getGoalTime());
        prevStudyRoom.setUrl(studyRoomUpdateRequest.getUrl());
    }

    @Transactional
    public void deleteStudyRoom(Integer id) {
        Optional<StudyRoom> studyRoomOptional = studyRoomRepository.findById(id);
        StudyRoom studyRoom = studyRoomOptional.get();
        studyRoom.setIsExist(0);
    }
}
