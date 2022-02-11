package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.FileInfo;
import com.ssafy.mogakgong.repository.CommunityRepository;
import com.ssafy.mogakgong.repository.FileRepository;
import com.ssafy.mogakgong.request.FileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public void saveFile(Integer communityId, List<FileRequest> files) {
        for (FileRequest fileRequest : files) {
            // 파일 경로
            String folder = "뭐 넣지..";

            // 원본 파일 이름
            String originFile = fileRequest.getOriginFile();

            // 저장할 파일 이름 : UUID + 확장자
            String uuid = UUID.randomUUID().toString();
            String ext = originFile.substring(originFile.lastIndexOf("."));
            String saveFile = uuid + ext;

            FileInfo file = FileInfo.builder()
                    .community(communityRepository.findById(communityId).get())
                    .saveFolder(folder)
                    .originFile(originFile)
                    .saveFile(saveFile)
                    .build();

            fileRepository.save(file);
        }
    }
}
