package com.ufro.culmingapp.student.infrastructure;

import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedAssistancesDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedEvaluationsDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedHomeworksDTO;
import com.ufro.culmingapp.student.application.StudentAssistanceFinder;
import com.ufro.culmingapp.student.application.StudentFinderService;
import com.ufro.culmingapp.student.application.StudentHomeworkFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class StudentGetController {

    @Autowired
    private StudentFinderService finder;

    @Autowired
    private StudentHomeworkFinderService studentHomeworksFinder;

    @Autowired
    private StudentAssistanceFinder studentAssistancesFinder;

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

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/students-assistances/{month}/{year}")
    public ResponseEntity<?> getAssistancesOfStudentsOfASubjectInACourse(
            @PathVariable Integer courseId, @PathVariable Integer subjectId,
            @PathVariable Integer month, @PathVariable Integer year) {
        List<StudentWithNestedAssistancesDTO> studentsAssistances = studentAssistancesFinder
                .getAssistancesOfStudentsOfASubjectInACourse(courseId, subjectId, month, year);
        return ResponseEntity.status(HttpStatus.OK).body(studentsAssistances);
    }

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/students/{studentId}/evaluations")
    public ResponseEntity<?> getStudentGradesOfEvaluationsInASubject(@PathVariable Integer courseId,
                                                                     @PathVariable Integer subjectId,
                                                                     @PathVariable Long studentId) {
        StudentWithNestedEvaluationsDTO studentEvaluations =
                finder.getEvaluationsOfStudentOfASubjectInACourse(courseId, subjectId, studentId);
        return ResponseEntity.status(HttpStatus.OK).body(studentEvaluations);
    }

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/students/{studentId}/workshops")
    public ResponseEntity<?> getStudentGradesOfWorkshopsInASubject(@PathVariable Integer courseId,
                                                                   @PathVariable Integer subjectId,
                                                                   @PathVariable Long studentId) {
        StudentWithNestedEvaluationsDTO studentEvaluations =
                finder.getWorkshopsOfStudentOfASubjectInACourse(courseId, subjectId, studentId);
        return ResponseEntity.status(HttpStatus.OK).body(studentEvaluations);
    }

}
