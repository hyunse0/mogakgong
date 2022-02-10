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
    private Integer level;

    @OneToOne
    @JoinColumn(name = "study_room_id")
    private StudyRoom studyRoom;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
