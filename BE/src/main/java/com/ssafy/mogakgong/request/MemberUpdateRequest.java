package com.ssafy.mogakgong.request;

import lombok.Data;

import java.sql.Date;

@Data
public class MemberUpdateRequest {
    String password;
    String nickname;
    String img;
    Date birth;
    String goal;
}
