package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.request.MemberJoinRequest;
import com.ssafy.mogakgong.request.MemberUpdateRequest;
import com.ssafy.mogakgong.response.MemberResponse;

import java.util.List;

public interface MemberService {

    public void join(MemberJoinRequest memberJoinRequest);
    public void validateDuplicateMember(Member member);
    public Boolean validatePassword(String password, String passwordCheck);
    public void checkPassword(String email, String password);
    public List<Member> findMembers();
    public Member findOne(Integer memberId);
    public List<String> getCategories(Integer memberId);
    public MemberResponse getMember(Member member, List<String> categories);
    public void update(Integer id, MemberUpdateRequest memberUpdateRequest);
    public void updateCategory(Integer memberId, List<String> memberCategories);
    public void delete(Integer id);
    public Member tokenToId(String jwtToken);
}
