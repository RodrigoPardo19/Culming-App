package com.ufro.culmingapp.homework.infrastructure;

import java.time.format.DateTimeParseException;

import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.homework.application.HomeworkUpdater;
import com.ufro.culmingapp.homework.application.DTOs.HomeworkDTO;
import com.ufro.culmingapp.homework.application.DTOs.HomeworkWithSubjectWithCourseDTO;
import com.ufro.culmingapp.homework.domain.HomeworkDeadline;
import com.ufro.culmingapp.homework.domain.HomeworkInstruction;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class HomeworkPatchController {

    @Autowired
    private HomeworkUpdater updater;

    @PatchMapping("/homeworks/{id}")
    public ResponseEntity<?> changeEvaluation(@PathVariable Long id,
            @RequestBody HomeworkWithSubjectWithCourseDTO homework) {

        try {

            HomeworkInstruction instruction = new HomeworkInstruction(homework.getInstruction());
            HomeworkDeadline deadline = new HomeworkDeadline(homework.getDeadline());
            Integer subjectId = homework.getSubjectId();
            Integer courseId = homework.getCourseId();

            HomeworkDTO evaluationUpdated = updater.update(id, instruction, deadline, courseId, subjectId);

            // To do buscar http response para las modificaciones
            return ResponseEntity.status(HttpStatus.CREATED).body(evaluationUpdated);

        } catch (NullFieldNotPermitted e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (HomeworkNotFound | SubjectNotFound | CourseNotFound  e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }

    }
    
}
