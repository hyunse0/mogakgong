package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.FileInfo;
import com.ssafy.mogakgong.request.FileRequest;

import java.util.List;

public interface FileService {
    void saveFile(Integer communityId, List<FileRequest> files);
    void deleteFile(FileInfo file);
}
