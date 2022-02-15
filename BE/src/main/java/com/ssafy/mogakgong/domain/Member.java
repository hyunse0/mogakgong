package com.ssafy.mogakgong.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter @Setter
@ApiModel(value = "Member : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "회원 번호")
    private Integer id;

    @Column(name = "email")
    @ApiModelProperty(value = "회원 이메일")
    private String email;

    @Column(name = "password")
    @ApiModelProperty(value = "회원 비밀번호")
    private String password;

    @Column(name = "nickname")
    @ApiModelProperty(value = "회원 닉네임")
    private String nickname;

    @Column(name = "img")
    @ApiModelProperty(value = "회원 프로필 이미지")
    private String img;

    @Column(name = "birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "회원 생년월일")
    private Date birth;

    @Column(name = "goal")
    @ApiModelProperty(value = "회원 목표")
    private String goal;

    @Column (name = "reg_date")
    @ApiModelProperty(value = "회원 가입일")
    @CreationTimestamp
    private Timestamp regDate;

    @Column(name = "is_exist")
    @ApiModelProperty(value = "회원 탈퇴여부")
    private Integer isExist;

    @Column(name = "type")
    @ApiModelProperty(value = "회원 가입 방식")
    private String type;

    @Column(name = "role")
    @ApiModelProperty(value = "회원 등급")
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<MemberCategory> memberCategories = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
//    private List<StudyRoomMember> studyRoomMembers = new ArrayList<>();


    public Member() {
        this.role = "USER";
    }

    @Builder
    public Member(String email, String password, String nickname, String img, Date birth, String goal, Timestamp regDate, Integer isExist, String type) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.img = img;
        this.birth = birth;
        this.goal = goal;
        this.regDate = regDate;
        this.isExist = isExist;
        this.type = type;
        this.role = "USER";
    }

}
