package youngpeople.aliali.entity.club;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.BaseEntity;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String text;
    private Boolean fixed;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

}
