package youngpeople.aliali.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.member.Member;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResDto {

    private String message;
    private String nickname;
    private Integer profile;

    public MemberResDto(Member member) {
        this.message  = "successful";
        this.nickname = member.getNickname();
        this.profile = member.getProfile();
    }

}
