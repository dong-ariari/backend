package youngpeople.aliali.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youngpeople.aliali.dto.SchoolResDto;
import youngpeople.aliali.service.SchoolService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping("/list")
    public List<SchoolResDto> getAll() {
        return schoolService.findAll();
    }

}
