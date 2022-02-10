package com.ssafy.mogakgong.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyRoomCategory is a Querydsl query type for StudyRoomCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyRoomCategory extends EntityPathBase<StudyRoomCategory> {

    private static final long serialVersionUID = 1591684381L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyRoomCategory studyRoomCategory = new QStudyRoomCategory("studyRoomCategory");

    public final QCategory category;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QStudyRoom studyRoom;

    public QStudyRoomCategory(String variable) {
        this(StudyRoomCategory.class, forVariable(variable), INITS);
    }

    public QStudyRoomCategory(Path<? extends StudyRoomCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyRoomCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyRoomCategory(PathMetadata metadata, PathInits inits) {
        this(StudyRoomCategory.class, metadata, inits);
    }

    public QStudyRoomCategory(Class<? extends StudyRoomCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.studyRoom = inits.isInitialized("studyRoom") ? new QStudyRoom(forProperty("studyRoom"), inits.get("studyRoom")) : null;
    }

}

