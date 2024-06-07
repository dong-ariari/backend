package youngpeople.aliali.entity.clubmember;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * Qwithdraw is a Querydsl query type for withdraw
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qwithdraw extends EntityPathBase<withdraw> {

    private static final long serialVersionUID = -1706548284L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final Qwithdraw withdraw = new Qwithdraw("withdraw");

    public final youngpeople.aliali.entity.QBaseEntity _super = new youngpeople.aliali.entity.QBaseEntity(this);

    //inherited
    public final BooleanPath activated = _super.activated;

    public final youngpeople.aliali.entity.club.QClub club;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final youngpeople.aliali.entity.member.QMember member;

    public final EnumPath<youngpeople.aliali.entity.enumerated.ResultType> resultType = createEnum("resultType", youngpeople.aliali.entity.enumerated.ResultType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public Qwithdraw(String variable) {
        this(withdraw.class, forVariable(variable), INITS);
    }

    public Qwithdraw(Path<? extends withdraw> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public Qwithdraw(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public Qwithdraw(PathMetadata metadata, PathInits inits) {
        this(withdraw.class, metadata, inits);
    }

    public Qwithdraw(Class<? extends withdraw> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new youngpeople.aliali.entity.club.QClub(forProperty("club"), inits.get("club")) : null;
        this.member = inits.isInitialized("member") ? new youngpeople.aliali.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

