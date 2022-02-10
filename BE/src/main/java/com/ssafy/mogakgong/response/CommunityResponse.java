package com.ssafy.mogakgong.response;

import com.ssafy.mogakgong.domain.Community;
import com.ssafy.mogakgong.domain.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;

@Builder
@Getter
public class CommunityResponse {
    private Integer id;
    private Member member;
    private String title;
    private String content;
    private Timestamp regDate;
    private Timestamp editDate;
    private Integer isExist;
    private Page<CommentResponse> comments;

    public static CommunityResponse from(Community community) {
        return CommunityResponse.builder()
                .id(community.getId())
                .member(community.getMember())
                .title(community.getTitle())
                .content(community.getContent())
                .regDate(community.getRegDate())
                .editDate(community.getEditDate())
                .isExist(community.getIsExist())
                .build();
    }
}
