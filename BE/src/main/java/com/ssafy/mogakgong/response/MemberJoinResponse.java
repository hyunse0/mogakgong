package com.ssafy.mogakgong.response;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.QnA;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.sql.Timestamp;

@Builder
@Getter
public class MemberJoinResponse {
    private Integer id;
    private String email;
    private String password;
    private String nickname;
    private String img;
    private Date birth;
    private String goal;
    private Timestamp regDate;
    private Integer isExist;
    private String type;

    public static MemberJoinResponse from(Member member) {
        return MemberJoinResponse.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .img(member.getImg())
                .birth(member.getBirth())
                .goal(member.getGoal())
                .regDate(member.getRegDate())
                .isExist(member.getIsExist())
                .type(member.getType())
                .build();
    }

}
