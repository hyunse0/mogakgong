package com.ssafy.mogakgong.request;

import lombok.Data;

@Data
public class MemberJoinRequest {
    Integer id;
    String email;
    String password;
    String nickname;
    String img;
    String birth;
    String goal;
    Integer isExist;
    String type;
    String passwordCheck;

    public MemberJoinRequest() {
        this.isExist = 1;
        this.type = "mogakgong";
    }

}
