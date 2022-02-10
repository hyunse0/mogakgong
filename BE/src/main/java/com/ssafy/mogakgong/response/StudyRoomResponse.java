package com.ssafy.mogakgong.response;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyRoom;
import com.ssafy.mogakgong.domain.StudyRoomHashtag;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Getter
public class StudyRoomResponse {
    private Integer id;
    private String title;
    private String password;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;
    private Integer limitPeople;
    private String img;
    private Integer goalTime;
    private String url;
    private Integer isExist;
    private Member member;
    private List<StudyRoomHashtag> studyRoomHashtags;

    public static StudyRoomResponse from (StudyRoom studyRoom) {
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
                .build();
    }
}
