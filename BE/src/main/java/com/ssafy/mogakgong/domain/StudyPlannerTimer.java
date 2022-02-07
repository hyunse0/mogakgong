package com.ssafy.mogakgong.domain;

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
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "study_planner_id")
    private StudyPlanner studyPlanner;

    @Column (name = "start_time")
    private Timestamp startTime;

    @Column (name = "end_time")
    private Timestamp endTime;

}
