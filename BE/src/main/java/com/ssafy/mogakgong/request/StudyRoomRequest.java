package com.ssafy.mogakgong.request;

import lombok.Data;

@Data
public class StudyRoomRequest {
    String title;
    String password;
    String description;
    String startDate;
    String endDate;
    Integer limit_people;
    String img;
    Integer goalTime;
    String url;
    Integer memberId;
    String hashtag;
}
