package com.ssafy.mogakgong.response;

import com.ssafy.mogakgong.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.sql.Timestamp;

@Builder
@Getter
public class MemberUpdateResponse {
    private Integer id;
    private String password;
    private String nickname;
    private String img;
    private Date birth;
    private String goal;
    private Integer isExist;

    public static MemberUpdateResponse from(Member member) {
        return MemberUpdateResponse.builder()
                .id(member.getId())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .img(member.getImg())
                .birth(member.getBirth())
                .goal(member.getGoal())
                .isExist(member.getIsExist())
                .build();
    }
}
