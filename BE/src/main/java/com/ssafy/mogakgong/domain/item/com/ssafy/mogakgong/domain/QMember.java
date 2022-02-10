package com.ssafy.mogakgong.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 382384063L;

    public static final QMember member = new QMember("member1");

    public final DatePath<java.sql.Date> birth = createDate("birth", java.sql.Date.class);

    public final StringPath email = createString("email");

    public final StringPath goal = createString("goal");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath img = createString("img");

    public final NumberPath<Integer> isExist = createNumber("isExist", Integer.class);

    public final ListPath<MemberCategory, QMemberCategory> memberCategories = this.<MemberCategory, QMemberCategory>createList("memberCategories", MemberCategory.class, QMemberCategory.class, PathInits.DIRECT2);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final DateTimePath<java.sql.Timestamp> regDate = createDateTime("regDate", java.sql.Timestamp.class);

    public final StringPath role = createString("role");

    public final ListPath<StudyRoomMember, QStudyRoomMember> studyRoomMembers = this.<StudyRoomMember, QStudyRoomMember>createList("studyRoomMembers", StudyRoomMember.class, QStudyRoomMember.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

