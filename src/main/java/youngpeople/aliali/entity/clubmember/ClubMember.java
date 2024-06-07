package youngpeople.aliali.entity.clubmember;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.BaseEntity;
import youngpeople.aliali.entity.club.Club;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.entity.enumerated.MemberRole;

import static youngpeople.aliali.entity.enumerated.MemberRole.*;

@Entity
@Table(name = "club_member")
@NoArgsConstructor
@Data
public class ClubMember extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole = GENERAL;
    private String memberRoleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    public ClubMember(MemberRole memberRole, String memberRoleNamem, Member member, Club club) {
        this.memberRole = memberRole;
        this.memberRoleName = memberRoleName;
        this.member = member;
        this.club = club;
    }

}
