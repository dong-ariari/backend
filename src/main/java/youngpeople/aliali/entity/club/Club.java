package youngpeople.aliali.entity.club;

import jakarta.persistence.*;
import lombok.*;
import youngpeople.aliali.entity.BaseEntity;
import youngpeople.aliali.entity.club.Notice;
import youngpeople.aliali.entity.club.Post;
import youngpeople.aliali.entity.club.Recruitment;
import youngpeople.aliali.entity.clubmember.ClubMember;
import youngpeople.aliali.entity.enumerated.ClubTypeA;
import youngpeople.aliali.entity.enumerated.ClubTypeB;
import youngpeople.aliali.entity.member.School;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Club extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String profile;

    private String introduction;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClubTypeA clubTypeA;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClubTypeB clubTypeB;

    private String typeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "club")
    private List<ClubMember> clubMembers = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Recruitment> recruitments = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Post> posts = new ArrayList<>();

//    public Club(String name, ClubTypeA typeA, ClubTypeB typeB, String typeName, School school) {
//        this.name = name;
//        this.clubTypeA = typeA;
//        this.clubTypeB = typeB;
//        this.typeName = typeName;
//        this.school = school;
//    }

//    임시
    public Club(String name, ClubTypeA typeA, ClubTypeB typeB, String typeName) {
        this.name = name;
        this.clubTypeA = typeA;
        this.clubTypeB = typeB;
        this.typeName = typeName;
    }

}
