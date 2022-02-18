package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.QnA;
import com.ssafy.mogakgong.repository.QnARepository;
import com.ssafy.mogakgong.request.QnARequest;
import com.ssafy.mogakgong.response.QnAResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QnAServiceImpl implements QnAService{

    @Autowired
    private QnARepository qnaRepository;

    @Override
    public Integer writeQnA(QnARequest qnaRequest, Member member) {
        QnA qna = QnA.builder()
                .member(member)
                .title(qnaRequest.getTitle())
                .content(qnaRequest.getContent())
                .state(qnaRequest.getState())
                .isExist(qnaRequest.getIsExist())
                .build();

        qnaRepository.save(qna);

        return qna.getId();
    }

    @Override
    public Page<QnAResponse> getQnAList(Integer memberId, Pageable pageable) {
        return qnaRepository.findByMemberIdAndIsExistOrderByIdDesc(memberId, 1, pageable)
                .map(QnAResponse::from);
    }

    @Override
    public Optional<QnA> getQnA(Integer qnaId) {
        Optional<QnA> qna = qnaRepository.findById(qnaId);

        return qna;
    }

    @Override
    public void deleteQnA(Integer qnaId) {
        Optional<QnA> qnaOptional = qnaRepository.findById(qnaId);
        QnA qna = qnaOptional.get();
        qna.changeIsExist(0);
        qnaRepository.save(qna);
    }
}
