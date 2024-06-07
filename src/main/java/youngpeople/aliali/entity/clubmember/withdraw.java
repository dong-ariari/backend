package youngpeople.aliali.entity.clubmember;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.BaseEntity;
import youngpeople.aliali.entity.club.Club;
import youngpeople.aliali.entity.enumerated.ResultType;
import youngpeople.aliali.entity.member.Member;

import static jakarta.persistence.FetchType.LAZY;
import static youngpeople.aliali.entity.enumerated.ResultType.*;

/**
 * 보류 엔티티
 */
@Entity
@NoArgsConstructor
public class withdraw extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "withdraw_id")
    private Long id;

    private ResultType resultType = PENDENCY;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club")
    private Club club;
}
