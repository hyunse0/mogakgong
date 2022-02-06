package com.ssafy.mogakgong.response;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.QnA;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class QnAResponse {
    private Integer id;
    private Member member;
    private String title;
    private String content;
    private Integer state;
    private Timestamp regDate;
    private Integer isExist;

    public static QnAResponse from(QnA qna) {
        return QnAResponse.builder()
                .id(qna.getId())
                .member(qna.getMember())
                .title(qna.getTitle())
                .content(qna.getContent())
                .state(qna.getState())
                .regDate(qna.getRegDate())
                .isExist(qna.getIsExist())
                .build();
    }
}
