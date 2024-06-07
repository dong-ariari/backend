package youngpeople.aliali.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youngpeople.aliali.dto.BasicResDto;
import youngpeople.aliali.dto.ClubReqDto;
import youngpeople.aliali.dto.ClubResDto;
import youngpeople.aliali.entity.club.Club;
import youngpeople.aliali.entity.clubmember.ClubMember;
import youngpeople.aliali.entity.enumerated.MemberRole;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.entity.member.School;
import youngpeople.aliali.repository.ClubMemberRepository;
import youngpeople.aliali.repository.ClubRepository;
import youngpeople.aliali.repository.MemberRepository;
import youngpeople.aliali.repository.SchoolRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClubService {

    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final ClubMemberRepository clubMemberRepository;
    private final SchoolRepository schoolRepository;

    public ClubResDto findClub(Long clubId) {
        Club club = clubRepository.findById(clubId).get();
        String schoolName = club.getSchool().getName();

        return new ClubResDto(club, schoolName);
    }

    public ClubResDto registerClub(ClubReqDto clubReqDto, String kakaoId) {
        Member member = memberRepository.findByKakaoId(kakaoId).get();

        School school = null;
        String schoolName = clubReqDto.getSchoolName();
        if (clubReqDto.getSchoolName() != null) {
            school = schoolRepository.findByName(schoolName).get();
        }

        Club club = clubReqDto.toEntity(school);
        clubRepository.save(club);

        ClubMember clubMember = new ClubMember(MemberRole.ADMIN, "master", member, club);
        clubMemberRepository.save(clubMember);

        return new ClubResDto(club, schoolName);
    }

    public ClubResDto updateClub(Long clubId, ClubReqDto clubReqDto, String kakaoId) {
        Member member = memberRepository.findByKakaoId(kakaoId).get();
        Club club = clubRepository.findById(clubId).get();

        ClubMember clubMember = clubMemberRepository.findByMemberAndClub(member, club).get();
        if (clubMember.getMemberRole().equals(MemberRole.GENERAL)) {
            // 클럽 권한 예외 터뜨리기
            return null;
        }

        School school = null;
        String schoolName = clubReqDto.getSchoolName();
        if (schoolName != null) {
            school = schoolRepository.findByName(clubReqDto.getSchoolName()).get();
        }

        clubReqDto.updateEntity(club, school);

        return new ClubResDto(club, school.getName());
    }

    /**
     * admin 권한이 필요 (서비스 레이어에서 작업)
     * 보다 상세한 요구사항 필요 : 멤버가 admin 혼자일 경우 가능
     */
    public BasicResDto deleteClub(String kakaoId, Long clubId) {
        Member member = memberRepository.findByKakaoId(kakaoId).get();
        Club club = clubRepository.findById(clubId).get();

        ClubMember clubMember = clubMemberRepository.findByMemberAndClub(member, club).get();
        if (!clubMember.getMemberRole().equals(MemberRole.ADMIN)) {
            // 클럽 권한 익셉션 터뜨리기
        }

        if (club.getClubMembers().size() > 1) {
            // 클럽 삭제 실패 익셉션 터뜨리기
        }

        club.setActivated(false);

        return BasicResDto.builder()
                .message("successful")
                .build();
    }

}
