package com.ssafy.mogakgong.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "study_room_hashtag")
@Getter @Setter
@ApiModel(value = "StudyRoomHashtag : 스터디룸의 해시태그", description = "스터디룸의 해시태그를 나타낸다.")
public class StudyRoomHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "해시태그 번호")
    private Integer id;

    @Column(name = "name")
    @ApiModelProperty(value = "해시태그 이름")
    private String name;

    @ManyToOne
    @JoinColumn(name = "study_room_id")
    @ApiModelProperty(value = "해당 해시태그를 포함하는 스터디룸")
    private StudyRoom studyRoom;

    public StudyRoomHashtag() {
    }

    @Builder
    public StudyRoomHashtag(String name, StudyRoom studyRoom) {
        this.name = name;
        this.studyRoom = studyRoom;
    }
}
