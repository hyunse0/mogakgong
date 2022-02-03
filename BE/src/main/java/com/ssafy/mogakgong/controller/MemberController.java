package com.ssafy.mogakgong.controller;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<String> createMember(@RequestBody Member member, @RequestBody String passwordCheck) {
        if(!memberService.checkPassword(member.getPassword(), passwordCheck)) { // 비밀번호 일치 체크
            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
        }
        try {
            memberService.join(member);  // 데이터 저장
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

//    // 회원가입
//    @PostMapping("/join")
//    public ResponseEntity<String> createMember(
//            @RequestBody @Valid CreateMemberRequest request,
//            @RequestBody PasswordCheckRequest passwordRequest ) {
//        if(!memberService.checkPassword(request.getPassword(), passwordRequest.getPasswordCheck())) { // 비밀번호 일치 체크
//            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
//        }
//        try {
//            Member member = new Member();
//            member.setEmail(request.getEmail());
//            member.setPassword(request.getPassword());
//            member.setNickname(request.getNickname());
//            member.setImg(request.getImg());
//            member.setBirth(Date.valueOf(request.getBirth()));
//            member.setGoal(request.getGoal());
//            memberService.join(member);  // 데이터 저장
//        } catch (IllegalStateException e) {
//            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//    }
//
//    @Data
//    static class CreateMemberRequest {
//        private String email;
//        private String password;
//        private String nickname;
//        private String img;
//        private String birth;
//        private String goal;
//    }

    @Data
    static class PasswordCheckRequest {
        private String passwordCheck;
    }

    // 비밀번호 확인
    @PostMapping("/check")
    public ResponseEntity<String> validatePassword(@RequestBody String memberEmail, @RequestBody String memberPassword) {
        try {
            memberService.validatePassword(memberEmail, memberPassword);  // 데이터 저장
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> loginMember(@RequestBody String memberEmail, @RequestBody String memberPassword) {
        try {
            memberService.validatePassword(memberEmail, memberPassword);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 회원정보 수정
    @PutMapping("/{memberId}")
    public ResponseEntity<String> updateMember(@PathVariable("memberId") int memberId, @RequestBody Member member) {
        memberService.update(memberId, member);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 회원정보 탈퇴
    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable("memberId") int memberId) {
        memberService.delete(memberId);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 회원정보 불러오기
    //@GetMapping("/{memberId}/profile")


}