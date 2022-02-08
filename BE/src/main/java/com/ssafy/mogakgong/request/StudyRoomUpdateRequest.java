package com.ssafy.mogakgong.request;

import lombok.Data;

@Data
public class StudyRoomUpdateRequest {
        String title;
        String password;
        String description;
        String startDate;
        String endDate;
        Integer limit;
        String img;
        Integer goalTime;
        String url;
}
