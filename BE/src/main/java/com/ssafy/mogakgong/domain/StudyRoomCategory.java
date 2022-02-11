package com.ssafy.mogakgong.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "study_room_category")
@Getter @Setter
@ApiModel(value = "StudyRoomCategory : 스터디룸의 카테고리", description = "스터디룸의 카테고리를 나타낸다.")
public class StudyRoomCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "스터디룸의 카테고리 번호")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "study_room_id")
    @ApiModelProperty(value = "스터디룸 번호")
    private StudyRoom studyRoom;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ApiModelProperty(value = "카테고리 번호")
    private Category category;

}
