package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Comment;
import com.ssafy.mogakgong.domain.Community;
import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.request.CommentRequest;

import java.util.Optional;

public interface CommentService {
    Integer writeComment(CommentRequest commentRequest, Member member, Community community);
    Optional<Comment> getComment(Integer commentId);
    void modifyComment(Integer commentId, CommentRequest commentRequest);
    void deleteComment(Integer commentId);
}
