package youngpeople.aliali.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.member.School;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolResDto {
    private Long id;
    private String name;

    public SchoolResDto(School school) {
        id = school.getId();
        name = school.getName();
    }

}
