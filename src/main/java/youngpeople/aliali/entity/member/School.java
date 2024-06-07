package youngpeople.aliali.entity.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.BaseEntity;

@Entity
@NoArgsConstructor
@Getter
public class School extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long id;

    private String name;
    private String email;

    public School(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
