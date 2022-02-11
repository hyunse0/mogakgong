package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Comment;
import com.ssafy.mogakgong.domain.Community;
import com.ssafy.mogakgong.domain.FileInfo;
import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.repository.CommentRepository;
import com.ssafy.mogakgong.repository.CommunityRepository;
import com.ssafy.mogakgong.repository.FileRepository;
import com.ssafy.mogakgong.request.CommunityRequest;
import com.ssafy.mogakgong.response.CommentResponse;
import com.ssafy.mogakgong.response.CommunityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceImpl implements CommunityService{

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Integer writeCommunity(CommunityRequest communityRequest, Member member) {
        Community community = Community.builder()
                .member(member)
                .title(communityRequest.getTitle())
                .content(communityRequest.getContent())
                .isExist(communityRequest.getIsExist())
                .build();

        communityRepository.save(community);

        return community.getId();
    }

    @Override
    public Page<CommunityResponse> getCommunityList(Pageable pageable) {
        return communityRepository.findByIsExistOrderByIdDesc(1, pageable)
                .map(CommunityResponse::from);
    }

    @Override
    public CommunityResponse getCommunity(Integer communityId, Pageable pageable) {
        Community community = communityRepository.findById(communityId).get();
        Page<CommentResponse> comments = commentRepository.findByCommunityIdAndIsExistOrderByIdDesc(communityId, 1, pageable)
                .map(CommentResponse::from);
        List<FileInfo> files = fileRepository.findByCommunityId(communityId);

        return CommunityResponse.builder()
                .id(community.getId())
                .member(community.getMember())
                .title(community.getTitle())
                .content(community.getContent())
                .regDate(community.getRegDate())
                .editDate(community.getEditDate())
                .isExist(community.getIsExist())
                .comments(comments)
                .files(files)
                .build();
    }

    @Override
    public void modifyCommunity(Integer communityId, CommunityRequest communityRequest) {
        Optional<Community> communityOptional = communityRepository.findById(communityId);
        Community community = communityOptional.get();
        community.modifyTitleContent(communityRequest.getTitle(), communityRequest.getContent());
        communityRepository.save(community);
    }

    @Override
    public void deleteCommunity(Integer communityId) {
        // 게시글 삭제
        Optional<Community> communityOptional = communityRepository.findById(communityId);
        Community community = communityOptional.get();
        community.changeIsExist(0);
        communityRepository.save(community);

        // 댓글 삭제
        List<Comment> comments = community.getComments();
        for (Comment comment : comments) {
            comment.changeIsExist(0);
            commentRepository.save(comment);
        }

        // 파일 삭제
        List<FileInfo> files = community.getFiles();
        for (FileInfo file : files) {
            fileRepository.delete(file);
        }
    }
}
