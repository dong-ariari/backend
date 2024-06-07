package youngpeople.aliali.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApply is a Querydsl query type for Apply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApply extends EntityPathBase<Apply> {

    private static final long serialVersionUID = 1529294762L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApply apply = new QApply("apply");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final BooleanPath activated = _super.activated;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final youngpeople.aliali.entity.member.QMember member;

    public final youngpeople.aliali.entity.club.QRecruitment recruitment;

    public final EnumPath<youngpeople.aliali.entity.enumerated.ResultType> result = createEnum("result", youngpeople.aliali.entity.enumerated.ResultType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QApply(String variable) {
        this(Apply.class, forVariable(variable), INITS);
    }

    public QApply(Path<? extends Apply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApply(PathMetadata metadata, PathInits inits) {
        this(Apply.class, metadata, inits);
    }

    public QApply(Class<? extends Apply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new youngpeople.aliali.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
        this.recruitment = inits.isInitialized("recruitment") ? new youngpeople.aliali.entity.club.QRecruitment(forProperty("recruitment"), inits.get("recruitment")) : null;
    }

}

