package com.ssafy.mogakgong.request;

import com.ssafy.mogakgong.domain.Member;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class StudyRoomRequest {
    String title;
    String password;
    String description;
    String startDate;
    String endDate;
    Integer limit;
    String img;
    Integer goalTime;
    String url;
    Integer memberId;
}
