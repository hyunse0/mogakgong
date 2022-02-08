package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Community;
import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.repository.CommentRepository;
import com.ssafy.mogakgong.repository.CommunityRepository;
import com.ssafy.mogakgong.request.CommunityRequest;
import com.ssafy.mogakgong.response.CommentResponse;
import com.ssafy.mogakgong.response.CommunityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommunityServiceImpl implements CommunityService{

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private CommentRepository commentRepository;

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
        Page<CommentResponse> comments =  commentRepository.findByCommunityIdAndIsExistOrderByIdDesc(communityId, 1, pageable)
                .map(CommentResponse::from);

        return CommunityResponse.builder()
                .id(community.getId())
                .member(community.getMember())
                .title(community.getTitle())
                .content(community.getContent())
                .regDate(community.getRegDate())
                .editDate(community.getEditDate())
                .isExist(community.getIsExist())
                .comments(comments)
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
        Optional<Community> communityOptional = communityRepository.findById(communityId);
        Community community = communityOptional.get();
        community.changeIsExist(0);
        communityRepository.save(community);
    }
}
