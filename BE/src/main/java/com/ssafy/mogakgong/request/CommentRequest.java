package com.ssafy.mogakgong.request;

import lombok.Data;

@Data
public class CommentRequest {
    Integer memberId;
    Integer communityId;
    String content;
    Integer isExist;
}
