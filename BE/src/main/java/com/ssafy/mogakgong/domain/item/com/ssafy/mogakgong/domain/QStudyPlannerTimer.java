package com.ssafy.mogakgong.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyPlannerTimer is a Querydsl query type for StudyPlannerTimer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyPlannerTimer extends EntityPathBase<StudyPlannerTimer> {

    private static final long serialVersionUID = 186719927L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyPlannerTimer studyPlannerTimer = new QStudyPlannerTimer("studyPlannerTimer");

    public final DateTimePath<java.sql.Timestamp> endTime = createDateTime("endTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> startTime = createDateTime("startTime", java.sql.Timestamp.class);

    public final QStudyPlanner studyPlanner;

    public QStudyPlannerTimer(String variable) {
        this(StudyPlannerTimer.class, forVariable(variable), INITS);
    }

    public QStudyPlannerTimer(Path<? extends StudyPlannerTimer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyPlannerTimer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyPlannerTimer(PathMetadata metadata, PathInits inits) {
        this(StudyPlannerTimer.class, metadata, inits);
    }

    public QStudyPlannerTimer(Class<? extends StudyPlannerTimer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyPlanner = inits.isInitialized("studyPlanner") ? new QStudyPlanner(forProperty("studyPlanner"), inits.get("studyPlanner")) : null;
    }

}

