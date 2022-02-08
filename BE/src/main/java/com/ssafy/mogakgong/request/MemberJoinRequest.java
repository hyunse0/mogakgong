package com.ssafy.mogakgong.request;

import lombok.Data;

import java.sql.Date;

@Data
public class MemberJoinRequest {
    Integer id;
    String email;
    String password;
    String nickname;
    String img;
    Date birth;
    String goal;
    Integer isExist;
    String type;
    String passwordCheck;
}
