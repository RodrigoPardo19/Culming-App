package com.ufro.culmingapp.homework.infrastructure;

import java.time.format.DateTimeParseException;

import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.homework.application.HomeworkCreator;
import com.ufro.culmingapp.homework.application.DTOs.HomeworkWithSubjectWithCourseDTO;
import com.ufro.culmingapp.homework.domain.HomeworkDeadline;
import com.ufro.culmingapp.homework.domain.HomeworkInstruction;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class HomeworkPostController {
    
    @Autowired
    private HomeworkCreator creator;

    @PostMapping("/homework")
    public ResponseEntity<?> createNewHomework(
            @RequestBody HomeworkWithSubjectWithCourseDTO newHomework) {

        try {

            HomeworkInstruction instruction = new HomeworkInstruction(newHomework.getInstruction()); 
            HomeworkDeadline deadline = new HomeworkDeadline(newHomework.getDeadline());
            Integer subjectId = newHomework.getSubjectId();
            Integer courseId = newHomework.getCourseId();

            creator.create(instruction, deadline, courseId, subjectId);

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (NullFieldNotPermitted e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (SubjectNotFound | CourseNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }

    }
}
