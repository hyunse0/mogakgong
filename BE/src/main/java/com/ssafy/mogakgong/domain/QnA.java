package com.ssafy.mogakgong.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "qna")
@ApiModel(value = "QnA : 1:1 문의 글 정보", description = "1:1문의 글의 상세 정보를 나타낸다.")
public class QnA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(value = "1:1 문의 글 번호")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "member_id")
    @ApiModelProperty(value = "1:1 문의 글 회원")
    private Member member;

    @NotNull
    @ApiModelProperty(value = "1:1 문의 글 제목")
    private String title;

    @NotNull
    @ApiModelProperty(value = "1:1 문의 글 내용")
    private String content;

    @NotNull
    @ApiModelProperty(value = "1:1 문의 글 상태(미답변/답변완료)")
    private Integer state;

    @CreationTimestamp
    @ApiModelProperty(value = "1:1 문의 글 등록일")
    @Column (name = "reg_date")
    private Timestamp regDate;

    @NotNull
    @ApiModelProperty(value = "1:1 문의 글 삭제 여부")
    @Column (name = "is_exist")
    private Integer isExist;

    @Builder
    public QnA(Integer id, Member member, String title, String content, Integer state, Timestamp regDate, Integer isExist) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.state = state;
        this.regDate = regDate;
        this.isExist = isExist;
    }

    // 삭제를 위한 편의 함수
    public void changeIsExist(Integer isExist) {
        this.isExist = isExist;
    }
}
