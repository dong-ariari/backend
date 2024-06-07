package youngpeople.aliali.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import youngpeople.aliali.dto.BasicResDto;
import youngpeople.aliali.dto.ClubReqDto;
import youngpeople.aliali.dto.ClubResDto;
import youngpeople.aliali.service.ClubService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    @GetMapping("/{clubId}")
    public ClubResDto getClub(@PathVariable(name = "clubId") Long clubId) {
        return clubService.findClub(clubId);
    }

    @PostMapping
    public ClubResDto registerClub(HttpServletRequest request,
                                   @RequestBody ClubReqDto clubReqDto) {
        String kakaoId = getKakaoId(request);

        return clubService.registerClub(clubReqDto, kakaoId);
    }


    @PutMapping("/{clubId}")
    public ClubResDto updateClub(HttpServletRequest request,
                                 @PathVariable(name = "clubId") Long clubId,
                                 @RequestBody ClubReqDto clubReqDto) {
        String kakaoId = getKakaoId(request);

        return clubService.updateClub(clubId, clubReqDto, kakaoId);
    }

    @DeleteMapping("/{clubId}")
    public BasicResDto deleteClub(HttpServletRequest request,
                                  @PathVariable(name = "clubId") Long clubId) {
        String kakaoId = getKakaoId(request);

        return clubService.deleteClub(kakaoId, clubId);
    }

    private String getKakaoId(HttpServletRequest request) {
        return (String) request.getAttribute("kakaoId");
    }

}
