package youngpeople.aliali.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.enumerated.MemberRole;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ClubMembersReqDto {

    private List<ClubMemberRequestDto> clubMembers = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class ClubMemberRequestDto {

        private Long clubMemberId;
        private MemberRole memberRole;
        private String memberRoleName;

    }

}