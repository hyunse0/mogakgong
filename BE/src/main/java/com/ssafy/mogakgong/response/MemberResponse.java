package com.ssafy.mogakgong.response;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.StudyRoom;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Builder
@Getter
public class MemberResponse {
    private Integer id;
    private String email;
    private String password;
    private String nickname;
    private String img;
    private Date birth;
    private String goal;
    private List<String> category;

    public static MemberResponse from (Member member, List<String> categories) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .img(member.getImg())
                .birth(member.getBirth())
                .goal(member.getGoal())
                .category(categories)
                .build();
    }
}
