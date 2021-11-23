package com.ufro.culmingapp.student.infrastructure;

import java.util.List;

import com.ufro.culmingapp.student.application.StudentFinderService;
import com.ufro.culmingapp.student.application.StudentHomeworkFinderService;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedEvaluationsDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedHomeworksDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class StudentGetController {

    @Autowired
    private StudentFinderService finder;

    @Autowired
    private StudentHomeworkFinderService studentHomeworksFinder;

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/students-evaluations")
    public ResponseEntity<?> getEvaluationsOfStudentsOfASubjectInACourse(
            @PathVariable Integer courseId, @PathVariable Integer subjectId) {
        List<StudentWithNestedEvaluationsDTO> studentsEvaluations = finder
                .getEvaluationsOfStudentsOfASubjectInACourse(courseId, subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(studentsEvaluations);
    }

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/students-workshops")
    public ResponseEntity<?> getWorkshopsOfStudentsOfASubjectInACourse(
            @PathVariable Integer courseId, @PathVariable Integer subjectId) {
        List<StudentWithNestedEvaluationsDTO> studentsEvaluations = finder
                .getWorkshopsOfStudentsOfASubjectInACourse(courseId, subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(studentsEvaluations);
    }

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/students-homeworks/{month}/{year}")
    public ResponseEntity<?> getHomeworksOfStudentsOfASubjectInACourse(
            @PathVariable Integer courseId, @PathVariable Integer subjectId, 
            @PathVariable Integer month, @PathVariable Integer year) {
        List<StudentWithNestedHomeworksDTO> studentsHomeworks = studentHomeworksFinder
                .getHomeworksOfStudentsOfASubjectInACourse(courseId, subjectId, month, year);
        return ResponseEntity.status(HttpStatus.OK).body(studentsHomeworks);
    }
}
