package youngpeople.aliali.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youngpeople.aliali.aop.alarm.AlarmInfo;
import youngpeople.aliali.dto.BasicResDto;
import youngpeople.aliali.entity.member.Alarm;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.exception.common.NotFoundEntityException;
import youngpeople.aliali.exception.common.NotMatchedEntitiesException;
import youngpeople.aliali.repository.MemberRepository;
import youngpeople.aliali.repository.AlarmRepository;

import java.util.List;
import java.util.Optional;

import static youngpeople.aliali.dto.AlarmDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AlarmService {

    private final MemberRepository memberRepository;
    private final AlarmRepository alarmRepository;

    public AlarmsResDto findAlarms(String kakaoId) {
        Member member = memberRepository.findByKakaoId(kakaoId).orElseThrow(NotFoundEntityException::new);
        return fromEntity("successful", member.getAlarms());
    }

    public BasicResDto checkAlarm(String kakaoId, Long signalId) {
        Member member = memberRepository.findByKakaoId(kakaoId).orElseThrow(NotFoundEntityException::new);
        Alarm alarm = alarmRepository.findById(signalId).orElseThrow(NotFoundEntityException::new);

        if (!member.equals(alarm.getMember())) {
            throw new NotMatchedEntitiesException();
        }

        alarm.setChecked(Boolean.TRUE);

        return BasicResDto.builder()
                .message("successful").build();
    }

    public void createAlarms(Long receiverId, AlarmInfo alarmInfo) {
        String message = alarmInfo.getMessage();
        String uri = alarmInfo.getUri();

        Member member = memberRepository.findById(receiverId).orElseThrow(NotFoundEntityException::new);
        Alarm alarm = new Alarm(message, uri, member);
        alarmRepository.save(alarm);
    }

}