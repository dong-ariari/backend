package youngpeople.aliali.entity.member;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youngpeople.aliali.entity.BaseEntity;
import youngpeople.aliali.entity.Apply;
import youngpeople.aliali.entity.clubmember.ClubMember;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Data
public class Member extends BaseEntity  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "kakao_id", nullable = false, unique = true)
    private String kakaoId;

    private Integer profile = 1;

    @Column(nullable = false, unique = true)
    private String nickname;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "member")
    private List<ClubMember> clubMembers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Apply> applies = new ArrayList<>();

    public Member(String kakaoId, String nickname) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
    }

    public String updateNickname(String nickname) {
        this.nickname = nickname;
        return nickname;
    }

}