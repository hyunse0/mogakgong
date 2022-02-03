package com.ssafy.mogakgong.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    private int id;

    private String password;

    private String nickname;

    private String img;

    private String birth; // 저장할 땐 date 타입으로 변환

    private String goal;

}
