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

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "comment")
@ApiModel(value = "Comment : 댓글 정보", description = "커뮤니티 게시글의 댓글 상세 정보를 나타낸다.")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(value = "댓글 번호")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "member_id")
    @ApiModelProperty(value = "댓글 작성자")
    private Member member;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "community_id")
    @ApiModelProperty(value = "댓글 작성된 게시글")
    private Community community;

    @NotNull
    @ApiModelProperty(value = "댓글 내용")
    private String content;

    @CreationTimestamp
    @ApiModelProperty(value = "댓글 등록일")
    @Column (name = "reg_date")
    private Timestamp regDate;

    @UpdateTimestamp
    @ApiModelProperty(value = "댓글 최근 수정일")
    @Column (name = "edit_date")
    private Timestamp editDate;

    @NotNull
    @ApiModelProperty(value = "댓글 삭제 여부")
    @Column (name = "is_exist")
    private Integer isExist;

    @Builder
    public Comment(Integer id, Member member, Community community, String content, Timestamp regDate, Timestamp editDate, Integer isExist) {
        this.id = id;
        this.member = member;
        this.community = community;
        this.content = content;
        this.regDate = regDate;
        this.editDate = editDate;
        this.isExist = isExist;
    }

    // 수정을 위한 편의 함수
    public void modifyContent(String content) {
        this.content = content;
    }

    // 삭제를 위한 편의 함수
    public void changeIsExist(Integer isExist) {
        this.isExist = isExist;
    }
}