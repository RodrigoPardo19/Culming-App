package com.ufro.culmingapp.assistance.infrastructure;

import com.ufro.culmingapp.assistance.application.AssistanceUpdater;
import com.ufro.culmingapp.assistance.application.DTOs.AssistanceDTO;
import com.ufro.culmingapp.assistance.application.DTOs.AssistanceWithCourseWithSubjectDTO;
import com.ufro.culmingapp.assistance.domain.AssistanceDate;
import com.ufro.culmingapp.assistance.domain.exceptions.AssistanceNotFound;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class AssistancePatchController {

    @Autowired
    private AssistanceUpdater updater;

    @PatchMapping("/assistances/{id}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<?> changeAssistance(@PathVariable Long id,
            @RequestBody AssistanceWithCourseWithSubjectDTO assistance) {
        try {
            AssistanceDate date = new AssistanceDate(assistance.getDate());
            Integer subjectId = assistance.getSubjectId();
            Integer courseId = assistance.getCourseId();
            AssistanceDTO assistanceUpdated = updater.update(id, date, courseId, subjectId);
            return ResponseEntity.status(HttpStatus.CREATED).body(assistanceUpdated);
        } catch (NullFieldNotPermitted | ParseException | AssistanceNotFound | SubjectNotFound | CourseNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }
}
