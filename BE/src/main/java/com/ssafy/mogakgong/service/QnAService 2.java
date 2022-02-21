package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.QnA;
import com.ssafy.mogakgong.request.QnARequest;
import com.ssafy.mogakgong.response.QnAResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface QnAService {
    Integer writeQnA(QnARequest qnaRequest, Member member);
    Page<QnAResponse> getQnAList(Integer memberId, Pageable pageable);
    Optional<QnA> getQnA(Integer qnaId);
    void deleteQnA(Integer qnaId);
}
