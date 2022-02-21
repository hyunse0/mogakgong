package com.ssafy.mogakgong.controller;

import com.ssafy.mogakgong.response.StudyRoomResponse;
import com.ssafy.mogakgong.service.StudyRoomServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class IndexController {

    private final StudyRoomServiceImpl studyRoomServiceImpl;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @GetMapping
    @ApiOperation(value = "인덱스 페이지", notes = "로그인 전 인덱스 페이지로 모든 스터디룸 정보를 반환한다.", response = Map.class)
    public ResponseEntity<Map<String, Object>> index(Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();

        Page<StudyRoomResponse> studyRoomList = studyRoomServiceImpl.getStudyRoomList(pageable);
        resultMap.put("totalStudyRoom", studyRoomList);
        resultMap.put("message", SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/main/{memberId}")
    @ApiOperation(value = "인덱스 페이지", notes = "로그인 후 인덱스 페이지로 모든, 추천, 입장이력이 있는 스터디룸 정보를 반환한다.", response = Map.class)
    public ResponseEntity<Map<String, Object>> loginIndex(@PathVariable("memberId") Integer memberId, Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();

        Page<StudyRoomResponse> totalStudyRoomList = studyRoomServiceImpl.getStudyRoomList(pageable);
        Page<StudyRoomResponse> recommendStudyRoomList = studyRoomServiceImpl.getRecommendStudyRoomList(memberId, pageable);
        Page<StudyRoomResponse> historyStudyRoomList = studyRoomServiceImpl.getHistoryStudyRoomList(memberId, pageable);
        resultMap.put("totalStudyRoom", totalStudyRoomList);
        resultMap.put("recommendStudyRoom", recommendStudyRoomList);
        resultMap.put("historyStudyRoom", historyStudyRoomList);
        resultMap.put("message", SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
