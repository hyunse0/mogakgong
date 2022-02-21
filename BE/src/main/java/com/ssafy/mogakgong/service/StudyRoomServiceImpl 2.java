package com.ssafy.mogakgong.service;

import com.querydsl.core.QueryResults;
import com.ssafy.mogakgong.domain.*;
import com.ssafy.mogakgong.repository.*;
import com.ssafy.mogakgong.request.StudyRoomRequest;
import com.ssafy.mogakgong.request.StudyRoomUpdateRequest;
import com.ssafy.mogakgong.response.StudyRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyRoomServiceImpl implements StudyRoomService {

    private final StudyRoomRepository studyRoomRepository;
    private final StudyRoomMemberRepository studyRoomMemberRepository;
    private final StudyRoomHashtagRepository studyRoomHashtagRepository;
    private final StudyRoomCategoryRepository studyRoomCategoryRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final CustomRepositoryImpl customRepository;
    private Integer exist = 1;

    @Transactional
    public Integer create(StudyRoomRequest studyRoomRequest, Member member) {
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
        //validateDuplicateStudyRoom(studyRoom);
        studyRoomRepository.save(studyRoom);

        for(String s : studyRoomRequest.getStudyRoomHashtags()){
            StudyRoomHashtag studyRoomHashtag = StudyRoomHashtag.builder()
                    .studyRoom(studyRoom)
                    .name(s)
                    .build();
            studyRoomHashtagRepository.save(studyRoomHashtag);
        }
        updateStudyRoomCategory(studyRoom.getId(), studyRoomRequest.getStudyRoomCategories());
        return studyRoom.getId();
    }

//    public void validateDuplicateStudyRoom(StudyRoom studyRoom) {
//        // 해당 url 을 사용중인 스터디룸 탐색
//        StudyRoom findStudyRoom = studyRoomRepository.findByUrl(studyRoom.getUrl());
//        // 사용중인 스터디룸이 존재한다면
//        if ( findStudyRoom != null) {
//            throw new IllegalStateException("이미 존재하는 url 입니다.");
//        }
//    }

    public Page<StudyRoomResponse> getStudyRoomList(Pageable pageable) {

        Page<StudyRoomResponse> studyRoomResponses = studyRoomRepository.findByIsExistOrderByIdDesc(1, pageable)
                .map(StudyRoomResponse::from);
        for(StudyRoomResponse response : studyRoomResponses){
            response.setStudyRoomHashtags(getStudyRoomHashtags(response.getId()));
            response.setStudyRoomCategories(getStudyRoomCategories(response.getId()));
        }
        return studyRoomResponses;
    }

    public Page<StudyRoomResponse> getRecommendStudyRoomList(Integer memberId, Pageable pageable) {

        List<StudyRoom> recommendList = customRepository.findByRecommend(memberId);
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > recommendList.size() ? recommendList.size() : (start + pageable.getPageSize());
        Page<StudyRoom> studyRooms =
                new PageImpl<>(recommendList.subList(start, end), pageable, recommendList.size());
        Page<StudyRoomResponse> studyRoomResponses = studyRooms.map(StudyRoomResponse::from);
        for(StudyRoomResponse response : studyRoomResponses){
            response.setStudyRoomHashtags(getStudyRoomHashtags(response.getId()));
            response.setStudyRoomCategories(getStudyRoomCategories(response.getId()));
        }
        return studyRoomResponses;
    }

    public Page<StudyRoomResponse> getHistoryStudyRoomList(Integer memberId, Pageable pageable) {
        List<StudyRoomMember> studyRoomMembers = studyRoomMemberRepository.findByMemberId(memberId);
        List<StudyRoom> historyList = new ArrayList<>();
        for(StudyRoomMember studyRoomMember : studyRoomMembers) {
            historyList.add(studyRoomMember.getStudyRoom());
        }
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > historyList.size() ? historyList.size() : (start + pageable.getPageSize());
        Page<StudyRoom> studyRooms =
                new PageImpl<>(historyList.subList(start, end), pageable, historyList.size());
        Page<StudyRoomResponse> studyRoomResponses = studyRooms.map(StudyRoomResponse::from);
        for(StudyRoomResponse response : studyRoomResponses){
            response.setStudyRoomHashtags(getStudyRoomHashtags(response.getId()));
            response.setStudyRoomCategories(getStudyRoomCategories(response.getId()));
        }
        return studyRoomResponses;
    }

    public List<String> getStudyRoomCategories(Integer studyRoomId) {
        List<String> categories = new ArrayList<>();
        List<StudyRoomCategory> studyRoomCategories = studyRoomCategoryRepository.findByStudyRoomId(studyRoomId);
        if(studyRoomCategories != null) {
            for(StudyRoomCategory studyRoomCategory : studyRoomCategories) {
                Category findCategory = categoryRepository.findById(studyRoomCategory.getCategory().getId()).get();
                categories.add(findCategory.getName());
            }
        }
        return categories;
    }

    public List<String> getStudyRoomHashtags(Integer studyRoomId) {
        List<String> hashTags = new ArrayList<>();
        List<StudyRoomHashtag> studyRoomHashtags = studyRoomHashtagRepository.findByStudyRoomId(studyRoomId);
        if(studyRoomHashtags != null) {
            for(StudyRoomHashtag studyRoomHashtag : studyRoomHashtags) {
                hashTags.add(studyRoomHashtag.getName());
            }
        }
        return hashTags;
    }

    public StudyRoomResponse getStudyRoom(Integer studyRoomId, Pageable pageable, List<String> categories, List<String> hashtags) {
        StudyRoom studyRoom = studyRoomRepository.findById(studyRoomId).get();

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
                .isExist(studyRoom.getIsExist())
                .member(studyRoom.getMember())
                .studyRoomHashtags(hashtags)
                .studyRoomCategories(categories)
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
        updateStudyRoomCategory(id, studyRoomUpdateRequest.getStudyRoomCategories());
    }

    // 스터디룸 카테고리 추가
    @Transactional
    public void updateStudyRoomCategory(Integer studyRoomId, List<String> studyRoomCategories) {
        studyRoomCategoryRepository.deleteAllByStudyRoomId(studyRoomId); // 기존 카테고리 삭제
        for (String category : studyRoomCategories) {
            Category findCategory = categoryRepository.findFirstByName(category);
            if(findCategory == null) {
                System.out.println("카테고리 이름 틀림");
                continue;
            }
            StudyRoomCategory studyRoomCategory = new StudyRoomCategory();
            studyRoomCategory.setStudyRoom(studyRoomRepository.findById(studyRoomId).get());
            studyRoomCategory.setCategory(findCategory);
            studyRoomCategoryRepository.save(studyRoomCategory); // 새로운 카테고리 추가
        }
    }

    @Transactional
    public void deleteStudyRoom(Integer id) {
        Optional<StudyRoom> studyRoomOptional = studyRoomRepository.findById(id);
        StudyRoom studyRoom = studyRoomOptional.get();
        studyRoom.setIsExist(0);
        studyRoomCategoryRepository.deleteAllByStudyRoomId(id);
    }

    public void checkPass(String password, Integer studyRoomId) {
        StudyRoom findStudyRoom = studyRoomRepository.findById(studyRoomId).get();
        if(!findStudyRoom.getPassword().equals(password)) {
            throw new SecurityException("비밀번호가 틀립니다.");
        }
    }

    @Transactional
    public void enter(Integer studyRoomId, Integer memberId, Integer level) {

        StudyRoomMember checkStudyRoomMember = studyRoomMemberRepository.findByStudyRoomIdAndMemberId(studyRoomId, memberId);
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
