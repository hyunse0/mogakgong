package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileInfo, Integer> {
    List<FileInfo> findByCommunityId(Integer communityId);
}
