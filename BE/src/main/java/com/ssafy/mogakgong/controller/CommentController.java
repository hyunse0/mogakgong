package com.ssafy.mogakgong.controller;

import com.ssafy.mogakgong.domain.Comment;
import com.ssafy.mogakgong.domain.Community;
import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.repository.CommunityRepository;
import com.ssafy.mogakgong.request.CommentRequest;
import com.ssafy.mogakgong.service.CommentService;
import com.ssafy.mogakgong.service.CommunityService;
import com.ssafy.mogakgong.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommunityRepository communityRepository;

    private CommentService commentService;
    private CommunityService communityService;
    private MemberService memberService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @PostMapping
    @ApiOperation(value = "댓글 작성",  notes = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    public ResponseEntity<String> writeComment(@RequestBody CommentRequest commentRequest) {
        try {
            Pageable pageable = PageRequest.of(0, 10);

            Member member = memberService.findOne(commentRequest.getMemberId());
            Community community = communityRepository.findById(commentRequest.getCommunityId()).get();
            Integer commentId = commentService.writeComment(commentRequest, member, community); // 반환이 필요할 경우 반환, 아니면 지우기
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    @ApiOperation(value = "댓글 수정",  notes = "댓글 정보를 수정한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    public ResponseEntity<String> modifyComment(@PathVariable Integer commentId,
                                                @RequestBody CommentRequest commentRequest) {
        Map<String, Object> resultMap = new HashMap<>();

        Optional<Comment> comment = commentService.getComment(commentId);
        if (comment.get().getIsExist() == 0) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        } else {
            try {
                commentService.modifyComment(commentId, commentRequest);
            } catch (Exception e) {
                return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{commentId}")
    @ApiOperation(value = "댓글 삭제", notes = "댓글의 is_exist를 0로 변경한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    public ResponseEntity<String> deleteComment(@PathVariable Integer commentId) {
        try {
            commentService.deleteComment(commentId);
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
