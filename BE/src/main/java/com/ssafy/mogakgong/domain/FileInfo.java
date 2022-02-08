package com.ssafy.mogakgong.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "community_file_info")
@ApiModel(value = "FileInfo : 커뮤니티 게시글 파일 정보", description = "커뮤니티 게시글의 파일 상세 정보를 나타낸다.")
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(value = "파일 번호")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "community_id")
    @ApiModelProperty(value = "파일이 있는 게시글")
    private Community community;

    @NotNull
    @ApiModelProperty(value = "파일이 존재하는 폴더명")
    @Column (name = "save_folder")
    private String saveFolder;

    @NotNull
    @ApiModelProperty(value = "파일의 실제 이름")
    @Column (name = "origin_file")
    private String originFile;

    @NotNull
    @ApiModelProperty(value = "서버에 저장된 파일의 이름")
    @Column (name = "save_file")
    private String saveFile;

    @Builder
    public FileInfo(Integer id, Community community, String saveFolder, String originFile, String saveFile) {
        this.id = id;
        this.community = community;
        this.saveFolder = saveFolder;
        this.originFile = originFile;
        this.saveFile = saveFile;
    }
}
