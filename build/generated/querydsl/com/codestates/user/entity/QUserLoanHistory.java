package com.codestates.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLoanHistory is a Querydsl query type for UserLoanHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLoanHistory extends EntityPathBase<UserLoanHistory> {

    private static final long serialVersionUID = 2023567327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLoanHistory userLoanHistory = new QUserLoanHistory("userLoanHistory");

    public final com.codestates.baseentity.QBaseTimeEntity _super = new com.codestates.baseentity.QBaseTimeEntity(this);

    public final com.codestates.book.entity.QBook book;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.codestates.baseentity.QLibrary library;

    public final DatePath<java.time.LocalDate> loanDate = createDate("loanDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final DatePath<java.time.LocalDate> returnedDate = createDate("returnedDate", java.time.LocalDate.class);

    public final EnumPath<UserLoanStatus> status = createEnum("status", UserLoanStatus.class);

    public final QUser user;

    public QUserLoanHistory(String variable) {
        this(UserLoanHistory.class, forVariable(variable), INITS);
    }

    public QUserLoanHistory(Path<? extends UserLoanHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLoanHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLoanHistory(PathMetadata metadata, PathInits inits) {
        this(UserLoanHistory.class, metadata, inits);
    }

    public QUserLoanHistory(Class<? extends UserLoanHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new com.codestates.book.entity.QBook(forProperty("book")) : null;
        this.library = inits.isInitialized("library") ? new com.codestates.baseentity.QLibrary(forProperty("library")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

