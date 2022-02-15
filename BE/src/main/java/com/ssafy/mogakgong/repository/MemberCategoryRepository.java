package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.MemberCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCategoryRepository extends JpaRepository<MemberCategory, Integer> {
    List<MemberCategory> findByMemberId(Integer memberId);
    void deleteAllByMemberId(Integer memberId);
}
