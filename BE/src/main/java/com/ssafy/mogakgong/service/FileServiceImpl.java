package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.FileInfo;
import com.ssafy.mogakgong.repository.CommunityRepository;
import com.ssafy.mogakgong.repository.FileRepository;
import com.ssafy.mogakgong.request.FileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private FileRepository fileRepository;

    @Value("${file.directory}")
    String fileDir;

    @Override
    public void saveFile(Integer communityId, List<FileRequest> files) {
        for (FileRequest fileRequest : files) {
            // 원본 파일 이름
            String originFile = fileRequest.getOriginFile();

            // 저장할 파일 이름 : UUID + 확장자
            String uuid = UUID.randomUUID().toString();
            String ext = originFile.substring(originFile.lastIndexOf("."));
            String saveFile = uuid + ext;

            // 레포지토리에 저장
            FileInfo file = FileInfo.builder()
                    .community(communityRepository.findById(communityId).get())
                    .saveFolder(fileDir)
                    .originFile(originFile)
                    .saveFile(saveFile)
                    .build();

            fileRepository.save(file);

            // 해당 경로에 저장

        }
    }

    @Override
    public void deleteFile(FileInfo file) {
        fileRepository.delete(file);
    }
}
