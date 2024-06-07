package youngpeople.aliali.entity.appmanege;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.BaseEntity;

@Entity
@NoArgsConstructor
public class Policy extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id")
    private Long id;

    private String title;
    private String text;
    private Boolean fixed;

}
