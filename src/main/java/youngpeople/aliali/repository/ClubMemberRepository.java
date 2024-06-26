package youngpeople.aliali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngpeople.aliali.entity.club.Club;
import youngpeople.aliali.entity.clubmember.ClubMember;
import youngpeople.aliali.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {

    Optional<ClubMember> findByMemberAndClub(Member member, Club club);

    List<ClubMember> findByClub(Club club);
}
