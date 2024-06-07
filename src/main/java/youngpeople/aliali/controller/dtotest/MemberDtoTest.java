package youngpeople.aliali.controller.dtotest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.member.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDtoTest {

    private String nickname;
    private Integer profile;

    public MemberDtoTest(Member member) {
        this.nickname = member.getNickname();
        this.profile = member.getProfile();
    }
}
