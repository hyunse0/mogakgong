package com.ssafy.mogakgong.request;

import lombok.Data;

@Data
public class QnARequest {
    Integer memberId;
    String title;
    String content;
    Integer state;
    Integer isExist;
}
