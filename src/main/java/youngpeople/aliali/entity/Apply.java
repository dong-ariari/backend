package youngpeople.aliali.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.enumerated.ResultType;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.entity.club.Recruitment;

import static jakarta.persistence.FetchType.LAZY;
import static youngpeople.aliali.entity.enumerated.ResultType.*;

@Entity
@NoArgsConstructor
public class Apply extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ResultType result = PENDENCY;

    // FK
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recruitment")
    private Recruitment recruitment;

}
