package youngpeople.aliali.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youngpeople.aliali.dto.BasicResDto;
import youngpeople.aliali.dto.ClubMembersReqDto;
import youngpeople.aliali.dto.ClubMemberResDtos;
import youngpeople.aliali.entity.club.Club;
import youngpeople.aliali.entity.clubmember.ClubMember;
import youngpeople.aliali.entity.enumerated.MemberRole;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.repository.ClubMemberRepository;
import youngpeople.aliali.repository.ClubRepository;
import youngpeople.aliali.repository.MemberRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClubMemberService {

    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final ClubMemberRepository clubMemberRepository;

    public ClubMemberResDtos findClubMembers(String kakaoId, Long clubId) {
        Member member = memberRepository.findByKakaoId(kakaoId).get();
        Club club = clubRepository.findById(clubId).get();

        ClubMember myClubMember = clubMemberRepository.findByMemberAndClub(member, club).get();
        if (myClubMember.getMemberRole().equals(MemberRole.GENERAL)) {
            // 익셉션 터뜨리기.
        }

        List<ClubMember> clubMembers = club.getClubMembers();

        return new ClubMemberResDtos(
                "successful",
                clubMembers);
    }

    public BasicResDto updateClubMembers(ClubMembersReqDto clubMembersReqDto) {
        List<ClubMembersReqDto.ClubMemberRequestDto> requestDtos = clubMembersReqDto.getClubMembers();

        for (ClubMembersReqDto.ClubMemberRequestDto requestDto : requestDtos) {
            ClubMember clubMember = clubMemberRepository.findById(requestDto.getClubMemberId()).get();
            clubMember.setMemberRole(requestDto.getMemberRole());
            clubMember.setMemberRoleName(requestDto.getMemberRoleName());
        }

        return BasicResDto.builder()
                .message("successful")
                .build();
    }

    public BasicResDto deleteClubMember(ClubMembersReqDto clubMembersReqDto) {
        List<ClubMembersReqDto.ClubMemberRequestDto> requestDtos = clubMembersReqDto.getClubMembers();

        for (ClubMembersReqDto.ClubMemberRequestDto requestDto : requestDtos) {
            ClubMember clubMember = clubMemberRepository.findById(requestDto.getClubMemberId()).get();
            clubMember.setActivated(false);
        }

        return BasicResDto.builder()
                .message("successful")
                .build();
    }

    /**
     * admin은 탈퇴 불가
     */
    public BasicResDto unregister(String kakaoId, Long clubId) {
        Member member = memberRepository.findByKakaoId(kakaoId).get();
        Club club = clubRepository.findById(clubId).get();

        ClubMember clubMember = clubMemberRepository.findByMemberAndClub(member, club).get();

        if (clubMember.getMemberRole().equals(MemberRole.ADMIN)) {
            // 익셉션 터뜨리기
        }

        clubMember.setActivated(false);
        return BasicResDto.builder()
                .message("successful")
                .build();
    }

}
