package youngpeople.aliali.entity.club;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.BaseEntity;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
public class Recruitment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Recruitment_id")
    private Long id;

    private String text;
    private String posterAddress;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer limitPeople;

    private Long views = 0L;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_id")
    private Club club;


}