package com.ssafy.mogakgong.domain;

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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

    @Column (name = "reg_date")
    @CreationTimestamp
    private Timestamp regDate;

    @Column(name = "is_exist")
    private Integer isExist;

    @OneToMany(mappedBy = "studyPlanner")
    private List<StudyPlannerTimer> studyPlannerTimers = new ArrayList<>();
}
