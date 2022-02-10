package com.ssafy.mogakgong.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyPlanner is a Querydsl query type for StudyPlanner
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyPlanner extends EntityPathBase<StudyPlanner> {

    private static final long serialVersionUID = -357956306L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyPlanner studyPlanner = new QStudyPlanner("studyPlanner");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isExist = createNumber("isExist", Integer.class);

    public final QMember member;

    public final DateTimePath<java.sql.Timestamp> regDate = createDateTime("regDate", java.sql.Timestamp.class);

    public final ListPath<StudyPlannerTimer, QStudyPlannerTimer> studyPlannerTimers = this.<StudyPlannerTimer, QStudyPlannerTimer>createList("studyPlannerTimers", StudyPlannerTimer.class, QStudyPlannerTimer.class, PathInits.DIRECT2);

    public final StringPath subject = createString("subject");

    public QStudyPlanner(String variable) {
        this(StudyPlanner.class, forVariable(variable), INITS);
    }

    public QStudyPlanner(Path<? extends StudyPlanner> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyPlanner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyPlanner(PathMetadata metadata, PathInits inits) {
        this(StudyPlanner.class, metadata, inits);
    }

    public QStudyPlanner(Class<? extends StudyPlanner> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

