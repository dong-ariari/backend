package youngpeople.aliali.controller.dtotest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.club.Club;
import youngpeople.aliali.entity.clubmember.ClubMember;
import youngpeople.aliali.entity.enumerated.ClubTypeA;
import youngpeople.aliali.entity.enumerated.ClubTypeB;
import youngpeople.aliali.entity.enumerated.MemberRole;
import youngpeople.aliali.entity.member.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubRegistrationDtoTest {
    private Long memberId;
    private String name;
    private ClubTypeA clubTypeA;
    private ClubTypeB clubTypeB;
    private String typeName;
    private Long schoolId;

//    임시
    public Club createClub() {
        return new Club(
                this.getName(),
                this.getClubTypeA(),
                this.getClubTypeB(),
                this.typeName);
    }

    public ClubMember createClubMember(Member member, Club club) {

        return new ClubMember(MemberRole.ADMIN,
                "주인장",
                member,
                club);
    }

}
