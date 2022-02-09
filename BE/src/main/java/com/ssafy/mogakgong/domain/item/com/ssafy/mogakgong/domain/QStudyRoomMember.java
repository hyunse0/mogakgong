package com.ssafy.mogakgong.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyRoomMember is a Querydsl query type for StudyRoomMember
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyRoomMember extends EntityPathBase<StudyRoomMember> {

    private static final long serialVersionUID = 99251833L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyRoomMember studyRoomMember = new QStudyRoomMember("studyRoomMember");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final QMember member;

    public final QStudyRoom studyRoom;

    public QStudyRoomMember(String variable) {
        this(StudyRoomMember.class, forVariable(variable), INITS);
    }

    public QStudyRoomMember(Path<? extends StudyRoomMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyRoomMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyRoomMember(PathMetadata metadata, PathInits inits) {
        this(StudyRoomMember.class, metadata, inits);
    }

    public QStudyRoomMember(Class<? extends StudyRoomMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.studyRoom = inits.isInitialized("studyRoom") ? new QStudyRoom(forProperty("studyRoom"), inits.get("studyRoom")) : null;
    }

}

