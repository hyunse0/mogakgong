package com.ssafy.mogakgong.request;

import lombok.Data;

@Data
public class CommunityRequest {
    Integer memberId;
    String title;
    String content;
    Integer isExist;
}
