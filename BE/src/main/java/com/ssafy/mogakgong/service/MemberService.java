package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.request.MemberJoinRequest;
import com.ssafy.mogakgong.request.MemberUpdateRequest;

import java.util.List;

public interface MemberService {

    public void join(MemberJoinRequest memberJoinRequest);
    public void validateDuplicateMember(Member member);
    public Boolean validatePassword(String password, String passwordCheck);
    public void checkPassword(String email, String password);
    public List<Member> findMembers();
    public Member findOne(Integer memberId);
    public void update(Integer id, MemberUpdateRequest memberUpdateRequest);
    public void delete(Integer id);
    public Member tokenToId(String jwtToken);
}
