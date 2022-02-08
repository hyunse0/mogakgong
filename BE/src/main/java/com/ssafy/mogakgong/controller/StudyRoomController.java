package com.ssafy.mogakgong.controller;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.request.StudyRoomRequest;
import com.ssafy.mogakgong.request.StudyRoomUpdateRequest;
import com.ssafy.mogakgong.response.CommunityResponse;
import com.ssafy.mogakgong.response.StudyRoomResponse;
import com.ssafy.mogakgong.service.MemberService;
import com.ssafy.mogakgong.service.StudyRoomService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/studyroom")
@RequiredArgsConstructor
public class StudyRoomController {

    private final StudyRoomService studyRoomService;
    private final MemberService memberService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @PostMapping
    @ApiOperation(value = "스터디룸 생성", notes = "새로운 스터디룸을 생성한다. 그리고 DB 입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> createStudyRoom(@RequestBody StudyRoomRequest studyRoomRequest) {
        try {
            Member member = memberService.findOne(studyRoomRequest.getMemberId());
            studyRoomService.create(studyRoomRequest, member);
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "스터디룸 목록", notes = "모든 스터디룸 정보를 반환한다.", response = Map.class)
    public ResponseEntity<Map<String, Object>> getStudyRoomList(Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();

        Page<CommunityResponse> studyRoomList = studyRoomService.getStudyRoomList(pageable);
        resultMap.put("info", studyRoomList);
        resultMap.put("message", SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/{studyRoomId}")
    @ApiOperation(value = "스터디룸 보기", notes = "스터디룸 번호에 해당하는 스터디룸의 정보를 반환한다.", response = Map.class)
    public ResponseEntity<Map<String, Object>> getStudyRoom(@PathVariable("studyRoomId") Integer studyRoomId) {
        Map<String, Object> resultMap = new HashMap<>();

        Pageable pageable = PageRequest.of(0, 10);

        StudyRoomResponse studyRoom = studyRoomService.getStudyRoom(studyRoomId, pageable);

        if(studyRoom == null || studyRoom.getIsExist() == 0) {
            resultMap.put("message", FAIL);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        } else {
            resultMap.put("info", studyRoom);
            resultMap.put("message", SUCCESS);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        }
    }

    @PutMapping("/{studyRoomId}")
    @ApiOperation(value = "스터디룸 수정", notes = "새로운 스터디룸 정보를 입력한다. 그리고 DB 수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> modifyStudyRoom(@PathVariable("studyRoomId") Integer studyRoomId, @RequestBody StudyRoomUpdateRequest studyRoomUpdateRequest ) {
        studyRoomService.updateStudyRoom(studyRoomId, studyRoomUpdateRequest);
        return null;
    }

    @DeleteMapping("/{studyRoomId}")
    @ApiOperation(value = "스터디룸 삭제", notes = "스터디룸 번호에 해당하는 스터디룸의 정보를 삭제한다(is_exist 변경). 그리고 DB 삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> deleteStudyRoom(@PathVariable("studyRoomId") Integer studyRoomId ) {
        studyRoomService.deleteStudyRoom(studyRoomId);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

}
