package com.ssafy.mogakgong.controller;

import com.ssafy.mogakgong.domain.StudyPlanner;
import com.ssafy.mogakgong.service.StudyPlannerServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/studyplanner")
@RequiredArgsConstructor
public class StudyPlannerController {

    private final StudyPlannerServiceImpl studyPlannerServiceImpl;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private Integer isExist = 1;

    // 스터디 플래너 작성
    @PostMapping("")
    @ApiOperation(value = "스터디플래너 작성", notes = "새로운 스터디플래너 정보를 입력한다. 그리고 DB 수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> createStudyPlanner(@RequestBody StudyPlanner studyPlanner) {
        try {
            studyPlannerServiceImpl.write(studyPlanner);  // 데이터 저장
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 스터디 플래너 목록
    @GetMapping("/{memberId}")
    @ApiOperation(value = "스터디플래너 목록 불러오기", notes = "사용자의 스터디 플래너의 목록을 반환한다.", response = Map.class)
    public ResponseEntity<Map<String, Object>> getStudyPlannerList(@PathVariable int memberId) {
        Map<String, Object> resultMap = new HashMap<>();

        List<StudyPlanner> studyPlanners = studyPlannerServiceImpl.findStudyPlanners(memberId, isExist);

        resultMap.put("info", studyPlanners);
        resultMap.put("message", SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    // 스터디 플래너 수정
    @PutMapping("/{memberId}")
    @ApiOperation(value = "스터디플래너 작성", notes = "새로운 스터디플래너 정보를 입력한다. 그리고 DB 수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> updateStudyPlanner(@PathVariable("memberId") Integer memberId, @RequestBody StudyPlanner studyPlanner) {
        studyPlannerServiceImpl.update(memberId, studyPlanner);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 스터디 플래너 탈퇴
    @DeleteMapping("/{memberId}")
    @ApiOperation(value = "스터디플래너 작성", notes = "스터디플래너를 삭제한다(IsExist 0으로 변경). 그리고 DB 삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
    public ResponseEntity<String> deleteStudyPlanner(@PathVariable("memberId") Integer memberId) {
        studyPlannerServiceImpl.delete(memberId);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
}
