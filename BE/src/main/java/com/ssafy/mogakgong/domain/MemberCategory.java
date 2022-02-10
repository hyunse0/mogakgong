package com.ssafy.mogakgong.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member_category")
@Getter @Setter
public class MemberCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "멤버와 카테고리 테이블의 번호")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @ApiModelProperty(value = "해당 카테고리를 소유한 멤버의 ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ApiModelProperty(value = "해당 멤버가 소유한 카테고리 ID")
    private Category category;

}
