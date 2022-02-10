package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Comment;
import com.ssafy.mogakgong.domain.Community;
import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.repository.CommentRepository;
import com.ssafy.mogakgong.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Integer writeComment(CommentRequest commentRequest, Member member, Community community) {
        Comment comment = Comment.builder()
                .member(member)
                .community(community)
                .content(commentRequest.getContent())
                .isExist(commentRequest.getIsExist())
                .build();

        commentRepository.save(comment);

        return comment.getId();
    }

    @Override
    public Optional<Comment> getComment(Integer commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        return comment;
    }

    @Override
    public void modifyComment(Integer commentId, CommentRequest commentRequest) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Comment comment = commentOptional.get();
        comment.modifyContent(commentRequest.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Comment comment = commentOptional.get();
        comment.changeIsExist(0);
        commentRepository.save(comment);
    }
}
