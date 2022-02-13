package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyRoom;
import com.ssafy.mogakgong.domain.StudyRoomHashtag;
import com.ssafy.mogakgong.domain.StudyRoomMember;
import com.ssafy.mogakgong.repository.MemberRepository;
import com.ssafy.mogakgong.repository.StudyRoomHashtagRepository;
import com.ssafy.mogakgong.repository.StudyRoomMemberRepository;
import com.ssafy.mogakgong.repository.StudyRoomRepository;
import com.ssafy.mogakgong.request.StudyRoomRequest;
import com.ssafy.mogakgong.request.StudyRoomUpdateRequest;
import com.ssafy.mogakgong.response.CommunityResponse;
import com.ssafy.mogakgong.response.StudyRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyRoomServiceImpl implements StudyRoomService {

    private final StudyRoomRepository studyRoomRepository;
    private final StudyRoomMemberRepository studyRoomMemberRepository;
    private final StudyRoomHashtagRepository studyRoomHashtagRepository;
    private final MemberRepository memberRepository;
    private Integer exist = 1;

    @Transactional
    public void create(StudyRoomRequest studyRoomRequest, Member member) {
        StudyRoom studyRoom = StudyRoom.builder()
                .title(studyRoomRequest.getTitle())
                .password(studyRoomRequest.getPassword())
                .description(studyRoomRequest.getDescription())
                .startDate(Timestamp.valueOf(studyRoomRequest.getStartDate()))
                .endDate(Timestamp.valueOf(studyRoomRequest.getEndDate()))
                .limitPeople(studyRoomRequest.getLimit_people())
                .img(studyRoomRequest.getImg())
                .goalTime(studyRoomRequest.getGoalTime())
                .url(studyRoomRequest.getUrl())
                .isExist(exist)
                .member(member)
                .build();
        validateDuplicateStudyRoom(studyRoom);
        studyRoomRepository.save(studyRoom);

        for(String s : studyRoomRequest.getStudyRoomHashtags()){
            StudyRoomHashtag studyRoomHashtag = StudyRoomHashtag.builder()
                    .studyRoom(studyRoom)
                    .name(s)
                    .build();
            studyRoomHashtagRepository.save(studyRoomHashtag);
        }
    }

    public void validateDuplicateStudyRoom(StudyRoom studyRoom) {
        // 해당 url 을 사용중인 스터디룸 탐색
        StudyRoom findStudyRoom = studyRoomRepository.findByUrl(studyRoom.getUrl());
        // 사용중인 스터디룸이 존재한다면
        if ( findStudyRoom != null) {
            throw new IllegalStateException("이미 존재하는 url 입니다.");
        }
    }

    public StudyRoom findStudyRoomByUrl(String url) {
        // 해당 url 을 사용중인 스터디룸 탐색
        StudyRoom findStudyRoom = studyRoomRepository.findByUrl(url);
        // 사용중인 스터디룸이 존재한다면
        return findStudyRoom;
    }

    public Page<StudyRoomResponse> getStudyRoomList(Pageable pageable) {
        return studyRoomRepository.findByIsExistOrderByIdDesc(1, pageable)
                .map(StudyRoomResponse::from);
    }

    public StudyRoomResponse getStudyRoom(Integer studyRoomId, Pageable pageable) {
        StudyRoom studyRoom = studyRoomRepository.findById(studyRoomId).get();
        List<StudyRoomHashtag> studyRoomHashtags = studyRoomHashtagRepository.findByStudyRoomId(studyRoomId);

        return StudyRoomResponse.builder()
                .id(studyRoom.getId())
                .title(studyRoom.getTitle())
                .password(studyRoom.getPassword())
                .description(studyRoom.getDescription())
                .startDate(studyRoom.getStartDate())
                .endDate(studyRoom.getEndDate())
                .limitPeople(studyRoom.getLimitPeople())
                .img(studyRoom.getImg())
                .goalTime(studyRoom.getGoalTime())
                .url(studyRoom.getUrl())
                .member(studyRoom.getMember())
                .studyRoomHashtags(studyRoomHashtags)
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
        prevStudyRoom.setLimitPeople(studyRoomUpdateRequest.getLimitPeople());
        prevStudyRoom.setImg(studyRoomUpdateRequest.getImg());
        prevStudyRoom.setGoalTime(studyRoomUpdateRequest.getGoalTime());
        prevStudyRoom.setUrl(studyRoomUpdateRequest.getUrl());

        studyRoomHashtagRepository.deleteAllByStudyRoomId(id);
        for(String s : studyRoomUpdateRequest.getStudyRoomHashtags()){
            StudyRoomHashtag studyRoomHashtag = StudyRoomHashtag.builder()
                    .studyRoom(prevStudyRoom)
                    .name(s)
                    .build();
            studyRoomHashtagRepository.save(studyRoomHashtag);
        }
    }

    @Transactional
    public void deleteStudyRoom(Integer id) {
        Optional<StudyRoom> studyRoomOptional = studyRoomRepository.findById(id);
        StudyRoom studyRoom = studyRoomOptional.get();
        studyRoom.setIsExist(0);
    }

    @Transactional
    public void enter(Integer studyRoomId, Integer memberId, Integer level) {

        StudyRoomMember checkStudyRoomMember = studyRoomMemberRepository.findByMemberId(memberId);
        if(checkStudyRoomMember != null) { // 입장 이력이 있을 때 로직처리
            if(checkStudyRoomMember.getLevel() == 3){
                throw new IllegalStateException("블랙리스트 회원입니다.");
            }
            checkStudyRoomMember.setIsExist(1);
            return;
        }
        StudyRoomMember studyRoomMember = new StudyRoomMember();
        StudyRoom studyRoom = studyRoomRepository.findById(studyRoomId).get();
        Member member = memberRepository.findById(memberId).get();

        studyRoomMember.setLevel(level);
        studyRoomMember.setStudyRoom(studyRoom);
        studyRoomMember.setMember(member);
        studyRoomMember.setIsExist(1);
        studyRoomMemberRepository.save(studyRoomMember);
    }

    @Transactional
    public void changeAuthority(Integer studyRoomId, Integer memberId, Integer level) {
        StudyRoomMember studyRoomMember = studyRoomMemberRepository.findByStudyRoomIdAndMemberId(studyRoomId, memberId);
        studyRoomMember.setLevel(level);
    }

    @Transactional
    public void exit(Integer studyRoomId, Integer memberId) {
        StudyRoomMember studyRoomMember = studyRoomMemberRepository.findByStudyRoomIdAndMemberId(studyRoomId, memberId);
        studyRoomMember.setIsExist(0);
    }
}
