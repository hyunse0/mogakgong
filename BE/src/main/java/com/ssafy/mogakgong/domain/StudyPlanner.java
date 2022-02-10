package com.ssafy.mogakgong.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "study_planner")
@Getter @Setter
public class StudyPlanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "스터디 플래너의 번호")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @ApiModelProperty(value = "해당 스터디 플래너 소유자의 번호")
    private Member member;

    @Column(name = "subject")
    @ApiModelProperty(value = "스터디 플래너의 과목")
    private String subject;

    @Column(name = "content")
    @ApiModelProperty(value = "스터디 플래너의 내용")
    private String content;

    @Column (name = "reg_date")
    @ApiModelProperty(value = "스터디 플래너의 등록 날짜")
    @CreationTimestamp
    private Timestamp regDate;

    @Column(name = "is_exist")
    @ApiModelProperty(value = "스터디 플래너의 존재 여부")
    private Integer isExist;

    @OneToMany(mappedBy = "studyPlanner")
    private List<StudyPlannerTimer> studyPlannerTimers = new ArrayList<>();
}
