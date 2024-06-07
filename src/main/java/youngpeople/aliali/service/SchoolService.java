package youngpeople.aliali.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youngpeople.aliali.dto.SchoolResDto;
import youngpeople.aliali.entity.member.School;
import youngpeople.aliali.repository.SchoolRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public List<SchoolResDto> findAll() {

        List<School> schools = new ArrayList<>(schoolRepository.findAll());

        List<SchoolResDto> schoolResDtos = new ArrayList<>();
        for (School school : schools) {
            schoolResDtos.add(new SchoolResDto(school));
        }

        return schoolResDtos;
    }

    public School findByName(String name) {
        return schoolRepository.findByName(name).get();
    }
}
