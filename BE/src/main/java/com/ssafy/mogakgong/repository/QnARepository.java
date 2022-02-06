package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.Member;
import com.ssafy.mogakgong.domain.QnA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnARepository extends JpaRepository<QnA, Integer> {
    Page<QnA> findByMemberIdOrderByIdDesc(Integer memberId, Pageable pageable);
}
