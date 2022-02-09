package com.ssafy.mogakgong.response;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyRoom;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class StudyRoomResponse {
    private Integer id;
    private String title;
    private String password;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;
    private Integer limit;
    private String img;
    private Integer goalTime;
    private String url;
    private Integer isExist;
    private Member member;

    public static StudyRoomResponse from (StudyRoom studyRoom) {
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
                .isExist(studyRoom.getIsExist())
                .member(studyRoom.getMember())
                .build();
    }
}
