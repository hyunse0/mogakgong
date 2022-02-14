package com.ssafy.mogakgong.controller;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.QnA;
import com.ssafy.mogakgong.request.QnARequest;
import com.ssafy.mogakgong.response.QnAResponse;
import com.ssafy.mogakgong.service.MemberServiceImpl;
import com.ssafy.mogakgong.service.QnAService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Api
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/qna")
public class QnAController {

    private QnAService qnaService;
    private MemberServiceImpl memberServiceImpl;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @PostMapping
    @ApiOperation(value = "1:1 문의 글작성", notes = "새로운 문의글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    public ResponseEntity<String> writeQnA(@RequestBody QnARequest qnaRequest) {
        try {
            Member member = memberServiceImpl.findOne(qnaRequest.getMemberId());
            Integer qnaId = qnaService.writeQnA(qnaRequest, member); // 반환이 필요할 경우 반환, 아니면 변수 선언은 지우기
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping("/{memberId}")
    @ApiOperation(value = "1:1 문의 글목록", notes = "회원의 모든 문의글의 정보를 반환한다.", response = Map.class)
    public ResponseEntity<Map<String, Object>> getQnAList(@PathVariable Integer memberId,
                                                          @PageableDefault(size = 5) Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();

        Page<QnAResponse> qnaList = qnaService.getQnAList(memberId, pageable);
        resultMap.put("info", qnaList);
        resultMap.put("message", SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/{memberId}/detail/{qnaId}")
    @ApiOperation(value = "1:1 문의 글보기", notes = "글번호에 해당하는 문의글의 정보를 반환한다.", response = Map.class)
    public ResponseEntity<Map<String, Object>> getQnA(@PathVariable("memberId") Integer memberId,
                                                      @PathVariable("qnaId") Integer qnaId) {
        Map<String, Object> resultMap = new HashMap<>();

        Optional<QnA> qna = qnaService.getQnA(qnaId);
        if (qna.get().getIsExist() == 0) {
            resultMap.put("message", FAIL);
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        } else {
            resultMap.put("info", qna);
            resultMap.put("message", SUCCESS);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{qnaId}")
    @ApiOperation(value = "1:1 문의 글삭제", notes = "1:1문의 글 번호에 해당하는 문의 글의 is_exist를 0으로 변경 한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    public ResponseEntity<String> deleteQnA(@PathVariable Integer qnaId) {
        try {
            qnaService.deleteQnA(qnaId);
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
