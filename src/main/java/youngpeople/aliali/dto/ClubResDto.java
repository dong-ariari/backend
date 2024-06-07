package youngpeople.aliali.dto;

import lombok.*;
import youngpeople.aliali.entity.club.Club;
import youngpeople.aliali.entity.enumerated.ClubTypeA;
import youngpeople.aliali.entity.enumerated.ClubTypeB;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubResDto {

    private String name;
    private String profile;
    private String introduction;
    private ClubTypeA clubTypeA;
    private ClubTypeB clubTypeB;
    private String typeName;
    private String schoolName;

    public ClubResDto(Club club, String schoolName) {
        this.name = club.getName();
        this.profile = club.getProfile();
        this.introduction = club.getIntroduction();
        this.clubTypeA = club.getClubTypeA();
        this.clubTypeB = club.getClubTypeB();
        this.typeName = club.getTypeName();
        this.schoolName = schoolName;
    }
}
