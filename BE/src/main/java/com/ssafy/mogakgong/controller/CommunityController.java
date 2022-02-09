package com.ssafy.mogakgong.controller;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.request.CommunityRequest;
import com.ssafy.mogakgong.response.CommunityResponse;
import com.ssafy.mogakgong.service.CommunityService;
import com.ssafy.mogakgong.service.MemberServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private CommunityService communityService;
    private MemberServiceImpl memberServiceImpl;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @PostMapping
    @ApiOperation(value = "커뮤니티 게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    public ResponseEntity<String> writeCommunity(@RequestBody CommunityRequest communityRequest) {
        try {
            Member member = memberServiceImpl.findOne(communityRequest.getMemberId());
            Integer communityId = communityService.writeCommunity(communityRequest, member); // 반환이 필요할 경우 반환, 아니면 지우기
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "커뮤니티 게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = Map.class)
    public ResponseEntity<Map<String, Object>> getCommunityList(Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();

        Page<CommunityResponse> communityList = communityService.getCommunityList(pageable);
        resultMap.put("info", communityList);
        resultMap.put("message", SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/{communityId")
    @ApiOperation(value = "커뮤니티 게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Map.class)
    public ResponseEntity<Map<String, Object>> getCommunity(@PathVariable Integer communityId) {
        Map<String, Object> resultMap = new HashMap<>();

        Pageable pageable = PageRequest.of(0, 10);
        CommunityResponse community = communityService.getCommunity(communityId, pageable);

        if (community.getIsExist() == 0) {
            resultMap.put("message", FAIL);
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        } else {
            resultMap.put("info", community);
            resultMap.put("message", SUCCESS);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    }

    @PutMapping("/{communityId}")
    @ApiOperation(value = "커뮤니티 게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    public ResponseEntity<String> modifyCommunity(@PathVariable Integer communityId,
                                                  @RequestBody CommunityRequest communityRequest) {
        Map<String, Object> resultMap = new HashMap<>();

        Pageable pageable = PageRequest.of(0, 10);
        CommunityResponse community = communityService.getCommunity(communityId, pageable);

        if (community.getIsExist() == 0) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        } else {
            try {
                communityService.modifyCommunity(communityId, communityRequest);
            } catch (Exception e) {
                return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{communityId}")
    @ApiOperation(value = "커뮤니티 게시판 글삭제", notes = "게시글의 is_exist를 0로 변경한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    public ResponseEntity<String> deleteCommunity(@PathVariable Integer communityId) {
        try {
            communityService.deleteCommunity(communityId);
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
