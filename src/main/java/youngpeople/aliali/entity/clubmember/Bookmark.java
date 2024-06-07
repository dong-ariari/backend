package youngpeople.aliali.entity.clubmember;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.BaseEntity;
import youngpeople.aliali.entity.club.Club;
import youngpeople.aliali.entity.member.Member;

@Entity
@NoArgsConstructor
@Getter
public class Bookmark extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

}
