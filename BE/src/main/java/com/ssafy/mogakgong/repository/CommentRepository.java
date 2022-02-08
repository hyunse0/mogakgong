package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findByCommunityIdAndIsExistOrderByIdDesc(Integer communityId, Integer isExist, Pageable pageable);
}
