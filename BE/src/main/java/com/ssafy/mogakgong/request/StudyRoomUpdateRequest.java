package com.ssafy.mogakgong.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudyRoomUpdateRequest {
        String title;
        String password;
        String description;
        String startDate;
        String endDate;
        Integer limitPeople;
        String img;
        Integer goalTime;
        String url;
        List<String> studyRoomHashtags;

        public StudyRoomUpdateRequest() {
                this.studyRoomHashtags = new ArrayList<>();
        }
}
