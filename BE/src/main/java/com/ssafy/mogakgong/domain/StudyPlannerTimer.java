package com.ssafy.mogakgong.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "study_planner_timer")
@Getter @Setter
public class StudyPlannerTimer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "스터디플래너 타이머 번호")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "study_planner_id")
    @ApiModelProperty(value = "해당 스터디 플래너의 ID")
    private StudyPlanner studyPlanner;

    @Column (name = "start_time")
    @ApiModelProperty(value = "스터디 플래너의 시작 시간")
    private Timestamp startTime;

    @Column (name = "end_time")
    @ApiModelProperty(value = "스터디룸 플래너의 종료 시간")
    private Timestamp endTime;

}
