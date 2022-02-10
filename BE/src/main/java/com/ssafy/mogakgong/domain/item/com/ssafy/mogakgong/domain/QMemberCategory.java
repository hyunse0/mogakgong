package com.ssafy.mogakgong.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberCategory is a Querydsl query type for MemberCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberCategory extends EntityPathBase<MemberCategory> {

    private static final long serialVersionUID = 1041213917L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberCategory memberCategory = new QMemberCategory("memberCategory");

    public final QCategory category;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMember member;

    public QMemberCategory(String variable) {
        this(MemberCategory.class, forVariable(variable), INITS);
    }

    public QMemberCategory(Path<? extends MemberCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberCategory(PathMetadata metadata, PathInits inits) {
        this(MemberCategory.class, metadata, inits);
    }

    public QMemberCategory(Class<? extends MemberCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

