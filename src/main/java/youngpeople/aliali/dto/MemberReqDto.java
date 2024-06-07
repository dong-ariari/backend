package youngpeople.aliali.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberReqDto {

    private String nickname;
    private Integer profile;

}
