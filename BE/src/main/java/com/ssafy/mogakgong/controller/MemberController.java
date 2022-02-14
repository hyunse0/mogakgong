package com.ssafy.mogakgong.controller;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.request.MemberJoinRequest;
import com.ssafy.mogakgong.request.MemberProfileRequest;
import com.ssafy.mogakgong.request.MemberUpdateRequest;
import com.ssafy.mogakgong.service.MemberServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private Integer isExist = 1;

    // 회원가입
    @PostMapping("/join")
    @ApiOperation(value = "회원가입", notes = "새로운 회원 가입 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> createMember(@RequestBody MemberJoinRequest memberJoinRequest) {
        if(!memberServiceImpl.validatePassword(memberJoinRequest.getPassword(), memberJoinRequest.getPasswordCheck())) { // 비밀번호 일치 체크
            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
        }
        try {
            String rawPassword = memberJoinRequest.getPassword();
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
            memberJoinRequest.setPassword(encPassword);
            memberServiceImpl.join(memberJoinRequest);  // 데이터 저장
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>("ID_DUPLICATED", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 비밀번호 확인
    @PostMapping("/check")
    @ApiOperation(value = "비밀번호 확인", notes = "비밀번호를 확인한다. 인증 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> validatePassword(@RequestBody MemberProfileRequest memberProfileRequest) {
        try {
            memberServiceImpl.checkPassword(memberProfileRequest.getEmail(), memberProfileRequest.getPassword());
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 회원정보 수정
    @PutMapping("/{memberId}")
    @ApiOperation(value = "회원 수정", notes = "기존의 회원 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> updateMember(@PathVariable("memberId") Integer memberId, @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberServiceImpl.update(memberId, memberUpdateRequest);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 회원정보 탈퇴
    @DeleteMapping("/{memberId}")
    @ApiOperation(value = "회원 탈퇴", notes = "회원 컬럼의 is_exist를 0으로 수정한다. DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> deleteMember(@PathVariable("memberId") Integer memberId) {
        memberServiceImpl.delete(memberId);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 회원정보 불러오기
    @GetMapping("/{memberId}/profile")
    @ApiOperation(value = "회원 프로필 정보 갖고 오기", notes = "회원의 프로필 정보를 갖고 온다.(기본 회원 정보 + 카테고리 등)", response = Map.class)
    public ResponseEntity<Map<String, Object>> getMemberInfo(@PathVariable("memberID") Integer id, @PathVariable String memberId){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        Member member = memberServiceImpl.findOne(id);
        if ( member == null || member.getIsExist() != isExist) {
            resultMap.put("message", FAIL);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        resultMap.put("info", member);
        resultMap.put("message", SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 회원 ID 반환하기
    @GetMapping("")
    @ApiOperation(value = "회원 ID 정보 갖고 오기", notes = "회원의 ID 정보를 갖고 온다.", response = Map.class)
    public  ResponseEntity<Map<String, Object>> getMemberId(@RequestHeader HttpHeaders headers) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        int id = memberServiceImpl.tokenToId(headers.getFirst("Authorization"));
        resultMap.put("id", id);
        resultMap.put("message", SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}