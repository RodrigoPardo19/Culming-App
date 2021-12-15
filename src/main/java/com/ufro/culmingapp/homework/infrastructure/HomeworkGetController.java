package com.ufro.culmingapp.homework.infrastructure;

import java.util.List;

import com.ufro.culmingapp.homework.application.HomeworkFinder;
import com.ufro.culmingapp.homework.application.DTOs.HomeworkDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class HomeworkGetController {

    @Autowired
    private HomeworkFinder finder;

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/homeworks/{month}/{year}")
    @Secured({ "ROLE_TEACHER", "ROLE_STUDENT", "ROLE_TUTOR" })
    public ResponseEntity<?> getHomeworksOfASubjectInACourseByMonth(
            @PathVariable Integer courseId, @PathVariable Integer subjectId,
            @PathVariable Integer month, @PathVariable Integer year) {
        List<HomeworkDTO> homeworks = finder
                .getHomeworksOfASubjectInACouseByMonth(courseId, subjectId, month, year);
        return ResponseEntity.status(HttpStatus.OK).body(homeworks);
    }

}
