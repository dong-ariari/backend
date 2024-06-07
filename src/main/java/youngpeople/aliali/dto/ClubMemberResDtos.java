package youngpeople.aliali.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.clubmember.ClubMember;
import youngpeople.aliali.entity.enumerated.MemberRole;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ClubMemberResDtos {

    private String message;
    private List<ClubMemberResponseDto> clubMembers = new ArrayList<>();

    public ClubMemberResDtos(String message, List<ClubMember> clubMembers) {
        this.message = message;

        for (ClubMember clubMember : clubMembers) {
            this.clubMembers.add(new ClubMemberResponseDto(clubMember));
        }
    }

    @Data
    public static class ClubMemberResponseDto {
        private Long id;
        private String name;
        private MemberRole memberRole;
        private String memberRoleName;

        public ClubMemberResponseDto(ClubMember clubMember) {
            this.id = clubMember.getId();
            this.name = clubMember.getMember().getNickname();
            this.memberRole = clubMember.getMemberRole();
            this.memberRoleName = clubMember.getMemberRoleName();
        }
    }

}
