package com.ssafy.mogakgong.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Getter @Setter
@ApiModel(value = "Category : 카테고리 정보", description = "카테고리 상세 정보.")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "카테고리 번호")
    private Integer id;

    @Column(name = " name")
    @ApiModelProperty(value = "카테고리 이름")
    private String name;
}
