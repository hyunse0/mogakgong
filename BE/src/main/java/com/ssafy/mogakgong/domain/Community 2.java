package com.ssafy.mogakgong.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "community")
@ApiModel(value = "Community : 커뮤니티 게시글 정보", description = "커뮤니티 게시글의 상세 정보를 나타낸다.")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(value = "커뮤니티 게시글 번호")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "member_id")
    @ApiModelProperty(value = "커뮤니티 게시글 회원")
    private Member member;

    @NotNull
    @ApiModelProperty(value = "커뮤니티 게시글 제목")
    private String title;

    @NotNull
    @ApiModelProperty(value = "커뮤니티 게시글 내용")
    private String content;

    @CreationTimestamp
    @ApiModelProperty(value = "커뮤니티 게시글 등록일")
    @Column (name = "reg_date")
    private Timestamp regDate;

    @UpdateTimestamp
    @ApiModelProperty(value = "커뮤니티 게시글 최근 수정일")
    @Column (name = "edit_date")
    private Timestamp editDate;

    @NotNull
    @ApiModelProperty(value = "커뮤니티 게시글 삭제 여부")
    @Column (name = "is_exist")
    private Integer isExist;

    @OneToMany(mappedBy = "community", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ApiModelProperty(value = "커뮤니티 게시글 댓글들")
    private List<Comment> comments;

    @OneToMany(mappedBy = "community", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ApiModelProperty(value = "커뮤니티 게시글 파일들")
    private List<FileInfo> files;

    @Builder
    public Community(Integer id, Member member, String title, String content, Timestamp regDate, Timestamp editDate, Integer isExist) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.editDate = editDate;
        this.isExist = isExist;
    }

    // 수정을 위한 편의 함수
    public void modifyTitleContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 삭제를 위한 편의 함수
    public void changeIsExist(Integer isExist) {
        this.isExist = isExist;
    }
}
