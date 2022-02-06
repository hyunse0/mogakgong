package com.ssafy.mogakgong.controller;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    // 회원가입
    @PostMapping("/join")
    @ApiOperation(value = "회원가입", notes = "새로운 회원 가입 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> createMember(@RequestBody Member member) {
//        if(!memberService.checkPassword(member.getPassword(), passwordCheck)) { // 비밀번호 일치 체크
//            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
//        }
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

//    @Data
//    static class PasswordCheckRequest {
//        private String passwordCheck;
//    }

    // 비밀번호 확인
    @PostMapping("/check")
    @ApiOperation(value = "비밀번호 확인", notes = "비밀번호를 확인한다. 인증 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
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
    @ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
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
    @ApiOperation(value = "회원 수정", notes = "기존의 회원 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> updateMember(@PathVariable("memberId") Integer memberId, @RequestBody Member member) {
        memberService.update(memberId, member);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 회원정보 탈퇴
    @DeleteMapping("/{memberId}")
    @ApiOperation(value = "회원 탈퇴", notes = "회원 컬럼의 is_exist를 0으로 수정한다. DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> deleteMember(@PathVariable("memberId") Integer memberId) {
        memberService.delete(memberId);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 회원정보 불러오기
    @GetMapping("/{memberId}/profile")
    @ApiOperation(value = "회원 프로필 정보 갖고 오기", notes = "회원의 프로필 정보를 갖고 온다.(기본 회원 정보 + 카테고리 등)", response = Map.class)
    public ResponseEntity<Map<String, Object>> getMemberInfo(@PathVariable("memberID") Integer memberId){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        Member member = memberService.findOne(memberId);
        resultMap.put("info", member);
        resultMap.put("message", SUCCESS);
        status = HttpStatus.ACCEPTED;

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}