package youngpeople.aliali.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youngpeople.aliali.dto.ClubDto;
import youngpeople.aliali.dto.ClubDto.ClubsResDto;
import youngpeople.aliali.dto.MyDto.MyClubListResDto;
import youngpeople.aliali.entity.club.Apply;
import youngpeople.aliali.entity.clubmember.Bookmark;
import youngpeople.aliali.entity.clubmember.ClubMember;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.exception.common.NotFoundEntityException;
import youngpeople.aliali.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyService {

    private final MemberRepository memberRepository;

    public MyClubListResDto findMyClubs(String kakaoId) {
        Member member = memberRepository.findByKakaoId(kakaoId).orElseThrow(NotFoundEntityException::new);

        List<ClubMember> clubMembers = member.getClubMembers();
        List<Apply> applies = member.getApplies();

        return MyClubListResDto.builder()
                .message("successful")
                .clubMembers(clubMembers)
                .applies(applies).build();
    }


    public ClubsResDto findBookmarkClubs(String kakaoId) {
        Member member = memberRepository.findByKakaoId(kakaoId).get();
        List<Bookmark> bookmarks = member.getBookmarks();

        List<ClubDto.ClubResDto> clubResDtoList = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            clubResDtoList.add(ClubDto.ClubResDto.fromEntity(bookmark.getClub(), bookmark));
        }

        return new ClubsResDto("successful", clubResDtoList);
    }

}
