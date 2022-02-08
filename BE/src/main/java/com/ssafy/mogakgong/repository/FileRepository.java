package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileInfo, Integer> {
}
