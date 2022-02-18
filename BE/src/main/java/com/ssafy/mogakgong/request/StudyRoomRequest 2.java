package com.ssafy.mogakgong.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
    List<String> studyRoomHashtags;
    List<String> studyRoomCategories;

    public StudyRoomRequest() {
        this.studyRoomHashtags = new ArrayList<>();
        this.studyRoomCategories = new ArrayList<>();
    }
}
