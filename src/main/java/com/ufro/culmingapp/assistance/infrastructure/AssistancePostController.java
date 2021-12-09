package com.ufro.culmingapp.assistance.infrastructure;

import com.ufro.culmingapp.assistance.application.AssistanceCreator;
import com.ufro.culmingapp.assistance.application.DTOs.AssistanceWithCourseWithSubjectDTO;
import com.ufro.culmingapp.assistance.domain.AssistanceDate;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class AssistancePostController {

    @Autowired
    private AssistanceCreator creator;

    @PostMapping("/assistance")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<?> createNewAssistance(
            @RequestBody AssistanceWithCourseWithSubjectDTO assistance) {
        try {
            AssistanceDate date = new AssistanceDate(assistance.getDate());
            Integer courseId = assistance.getCourseId();
            Integer subjectId = assistance.getSubjectId();
            creator.create(date, courseId, subjectId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NullFieldNotPermitted | ParseException | SubjectNotFound | CourseNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }

}
