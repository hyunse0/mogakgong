package com.ssafy.mogakgong.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "study_room_member")
@Getter @Setter
@ApiModel(value = "StudyRoomMember : 스터디룸에 입장한 멤버들 정보", description = "스터디룸에 입장한 멤버들의 상세 정보를 나타낸다.")
public class StudyRoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "스터디룸 멤버 테이블의 번호")
    private Integer id;

    @Column(name = "level")
    @ApiModelProperty(value = "스터디룸 멤버들의 권한")
    private Integer level;

    @Column(name = "is_exist")
    @ApiModelProperty(value = "멤버의 현재 상태, 1: 입장 중, 2: 퇴장 중")
    private Integer isExist;

    @OneToOne
    @JoinColumn(name = "study_room_id")
    @ApiModelProperty(value = "해당 스터디룸의 ID")
    private StudyRoom studyRoom;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @ApiModelProperty(value = "해당 멤버들의 ID")
    private Member member;

}
