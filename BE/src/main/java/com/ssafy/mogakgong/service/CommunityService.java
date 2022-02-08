package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.request.CommunityRequest;
import com.ssafy.mogakgong.response.CommunityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunityService {
    Integer writeCommunity(CommunityRequest communityRequest, Member member);
    Page<CommunityResponse>  getCommunityList(Pageable pageable);
    CommunityResponse getCommunity(Integer communityId, Pageable pageable);
    void modifyCommunity(Integer communityId, CommunityRequest communityRequest);
    void deleteCommunity(Integer communityId);
}
