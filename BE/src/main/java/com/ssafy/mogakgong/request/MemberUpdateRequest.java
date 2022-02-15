package com.ssafy.mogakgong.request;

import com.ssafy.mogakgong.domain.MemberCategory;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class MemberUpdateRequest {
    String password;
    String nickname;
    String img;
    Date birth;
    String goal;
    List<String> memberCategories;
}
