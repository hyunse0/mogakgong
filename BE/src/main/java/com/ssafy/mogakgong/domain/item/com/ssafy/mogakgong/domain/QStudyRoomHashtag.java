package com.ssafy.mogakgong.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyRoomHashtag is a Querydsl query type for StudyRoomHashtag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyRoomHashtag extends EntityPathBase<StudyRoomHashtag> {

    private static final long serialVersionUID = -1469494323L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyRoomHashtag studyRoomHashtag = new QStudyRoomHashtag("studyRoomHashtag");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QStudyRoom studyRoom;

    public QStudyRoomHashtag(String variable) {
        this(StudyRoomHashtag.class, forVariable(variable), INITS);
    }

    public QStudyRoomHashtag(Path<? extends StudyRoomHashtag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyRoomHashtag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyRoomHashtag(PathMetadata metadata, PathInits inits) {
        this(StudyRoomHashtag.class, metadata, inits);
    }

    public QStudyRoomHashtag(Class<? extends StudyRoomHashtag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyRoom = inits.isInitialized("studyRoom") ? new QStudyRoom(forProperty("studyRoom"), inits.get("studyRoom")) : null;
    }

}

