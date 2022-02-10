package com.ssafy.mogakgong.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQnA is a Querydsl query type for QnA
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQnA extends EntityPathBase<QnA> {

    private static final long serialVersionUID = -79420737L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQnA qnA = new QQnA("qnA");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isExist = createNumber("isExist", Integer.class);

    public final QMember member;

    public final DateTimePath<java.sql.Timestamp> regDate = createDateTime("regDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public final StringPath title = createString("title");

    public QQnA(String variable) {
        this(QnA.class, forVariable(variable), INITS);
    }

    public QQnA(Path<? extends QnA> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQnA(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQnA(PathMetadata metadata, PathInits inits) {
        this(QnA.class, metadata, inits);
    }

    public QQnA(Class<? extends QnA> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

