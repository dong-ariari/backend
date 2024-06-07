package youngpeople.aliali.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.club.Club;
import youngpeople.aliali.entity.enumerated.ClubTypeA;
import youngpeople.aliali.entity.enumerated.ClubTypeB;
import youngpeople.aliali.entity.member.School;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubReqDto {

    private String name;
    private String profile;
    private String introduction;
    private ClubTypeA clubTypeA;
    private ClubTypeB clubTypeB;
    private String typeName;
    private String schoolName;

    // School 설정은 별도로
    public Club toEntity(School school) {
        return Club.builder()
                .name(name)
                .profile(profile)
                .introduction(introduction)
                .clubTypeA(clubTypeA)
                .clubTypeB(clubTypeB)
                .typeName(typeName)
                .school(school)
                .build();
    }

    public void updateEntity(Club club, School school) {
        club.setSchool(school);

        if (name != null) {
            club.setName(name);
        }
        if (profile != null) {
            club.setProfile(profile);
        }
        if (introduction != null) {
            club.setIntroduction(introduction);
        }
        if (clubTypeA != null) {
            club.setClubTypeA(clubTypeA);
        }
        if (clubTypeB != null) {
            club.setClubTypeB(clubTypeB);
        }
        if (typeName != null) {
            club.setTypeName(typeName);
        }
    }

}
