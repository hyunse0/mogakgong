package com.ssafy.mogakgong.request;

import lombok.Data;

import java.util.List;

@Data
public class CommunityRequest {
    Integer memberId;
    String title;
    String content;
    List<FileRequest> files;
    Integer isExist;
}
