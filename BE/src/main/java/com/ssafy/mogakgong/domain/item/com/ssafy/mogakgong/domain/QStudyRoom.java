package com.ssafy.mogakgong.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyRoom is a Querydsl query type for StudyRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyRoom extends EntityPathBase<StudyRoom> {

    private static final long serialVersionUID = -1947108609L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyRoom studyRoom = new QStudyRoom("studyRoom");

    public final StringPath description = createString("description");

    public final DateTimePath<java.sql.Timestamp> endDate = createDateTime("endDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> goalTime = createNumber("goalTime", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath img = createString("img");

    public final NumberPath<Integer> isExist = createNumber("isExist", Integer.class);

    public final NumberPath<Integer> limit = createNumber("limit", Integer.class);

    public final QMember member;

    public final StringPath password = createString("password");

    public final DateTimePath<java.sql.Timestamp> regDate = createDateTime("regDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> startDate = createDateTime("startDate", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    public final StringPath url = createString("url");

    public QStudyRoom(String variable) {
        this(StudyRoom.class, forVariable(variable), INITS);
    }

    public QStudyRoom(Path<? extends StudyRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyRoom(PathMetadata metadata, PathInits inits) {
        this(StudyRoom.class, metadata, inits);
    }

    public QStudyRoom(Class<? extends StudyRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

