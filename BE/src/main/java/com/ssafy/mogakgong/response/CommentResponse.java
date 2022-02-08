package com.ssafy.mogakgong.response;

import com.ssafy.mogakgong.domain.Comment;
import com.ssafy.mogakgong.domain.Community;
import com.ssafy.mogakgong.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class CommentResponse {
    private Integer id;
    private Member member;
    private Community community;
    private String content;
    private Timestamp regDate;
    private Timestamp editDate;
    private Integer isExist;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .member(comment.getMember())
                .community(comment.getCommunity())
                .content(comment.getContent())
                .regDate(comment.getRegDate())
                .editDate(comment.getEditDate())
                .isExist(comment.getIsExist())
                .build();
    }
}
