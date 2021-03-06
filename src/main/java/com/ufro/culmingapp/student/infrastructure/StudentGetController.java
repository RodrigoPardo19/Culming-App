package com.ufro.culmingapp.student.infrastructure;

import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.student.application.DTOs.*;
import com.ufro.culmingapp.student.application.StudentAssistanceFinder;
import com.ufro.culmingapp.student.application.StudentFinderService;
import com.ufro.culmingapp.student.application.StudentHomeworkFinderService;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

        @GetMapping("/courses/{courseId}/subjects/{subjectId}/students")
        @Secured({ "ROLE_STUDENT", "ROLE_TEACHER", "ROLE_TUTOR" })
        public ResponseEntity<?> getStudentsTakingASubjetInACourse(
                        @PathVariable Integer courseId, @PathVariable Integer subjectId) {
                List<StudentWithFullNameDTO> students = finder
                                .getStudentsWithFullNameTakingASubjectInACourse(courseId, subjectId);
                return ResponseEntity.status(HttpStatus.OK).body(students);
        }

        @GetMapping("/courses/{courseId}/subjects/{subjectId}/students-evaluations")
        @Secured("ROLE_TEACHER")
        public ResponseEntity<?> getEvaluationsOfStudentsOfASubjectInACourse(
                        @PathVariable Integer courseId, @PathVariable Integer subjectId) {
                List<StudentWithNestedEvaluationsDTO> studentsEvaluations = finder
                                .getEvaluationsOfStudentsOfASubjectInACourse(courseId, subjectId);
                return ResponseEntity.status(HttpStatus.OK).body(studentsEvaluations);
        }

        @GetMapping("/courses/{courseId}/subjects/{subjectId}/students-workshops")
        @Secured("ROLE_TEACHER")
        public ResponseEntity<?> getWorkshopsOfStudentsOfASubjectInACourse(
                        @PathVariable Integer courseId, @PathVariable Integer subjectId) {
                List<StudentWithNestedEvaluationsDTO> studentsEvaluations = finder
                                .getWorkshopsOfStudentsOfASubjectInACourse(courseId, subjectId);
                return ResponseEntity.status(HttpStatus.OK).body(studentsEvaluations);
        }

        @GetMapping("/courses/{courseId}/subjects/{subjectId}/students-homeworks/{month}/{year}")
        @Secured("ROLE_TEACHER")
        public ResponseEntity<?> getHomeworksOfStudentsOfASubjectInACourse(
                        @PathVariable Integer courseId, @PathVariable Integer subjectId, @PathVariable Integer month,
                        @PathVariable Integer year) {
                List<StudentWithNestedHomeworksDTO> studentsHomeworks = studentHomeworksFinder
                                .getHomeworksOfStudentsOfASubjectInACourse(courseId, subjectId, month, year);
                return ResponseEntity.status(HttpStatus.OK).body(studentsHomeworks);
        }

        @GetMapping("/courses/{courseId}/subjects/{subjectId}/students-assistances/{month}/{year}")
        @Secured("ROLE_TEACHER")
        public ResponseEntity<?> getAssistancesOfStudentsOfASubjectInACourse(
                        @PathVariable Integer courseId, @PathVariable Integer subjectId, @PathVariable Integer month,
                        @PathVariable Integer year) {
                List<StudentWithNestedAssistancesDTO> studentsAssistances = studentAssistancesFinder
                                .getAssistancesOfStudentsOfASubjectInACourse(courseId, subjectId, month, year);
                return ResponseEntity.status(HttpStatus.OK).body(studentsAssistances);
        }

        @GetMapping("/courses/{courseId}/subjects/{subjectId}/students/{studentId}/evaluations")
        @Secured({ "ROLE_STUDENT", "ROLE_TUTOR" })
        public ResponseEntity<?> getStudentGradesOfEvaluationsInASubject(@PathVariable Integer courseId,
                        @PathVariable Integer subjectId, @PathVariable Long studentId) {
                StudentWithNestedEvaluationsDTO studentEvaluations = finder
                                .getEvaluationsOfStudentOfASubjectInACourse(courseId, subjectId, studentId);
                return ResponseEntity.status(HttpStatus.OK).body(studentEvaluations);
        }

        @GetMapping("/courses/{courseId}/subjects/{subjectId}/students/{studentId}/workshops")
        @Secured({ "ROLE_STUDENT", "ROLE_TUTOR" })
        public ResponseEntity<?> getStudentGradesOfWorkshopsInASubject(@PathVariable Integer courseId,
                        @PathVariable Integer subjectId,
                        @PathVariable Long studentId) {
                StudentWithNestedEvaluationsDTO studentEvaluations = finder
                                .getWorkshopsOfStudentOfASubjectInACourse(courseId, subjectId, studentId);
                return ResponseEntity.status(HttpStatus.OK).body(studentEvaluations);
        }

        @GetMapping("/courses/{courseId}/subjects/{subjectId}/students/{studentId}/homeworks/{month}/{year}")
        @Secured({ "ROLE_STUDENT", "ROLE_TUTOR" })
        public ResponseEntity<?> getStudentHomeworksInASubject(@PathVariable Integer courseId,
                        @PathVariable Integer subjectId, @PathVariable Long studentId, @PathVariable Integer month,
                        @PathVariable Integer year) {
                StudentWithNestedHomeworksDTO studentHomeworks = studentHomeworksFinder
                                .getHomeworksOfStudentOfASubjectInACourse(courseId, subjectId, studentId, month, year);
                return ResponseEntity.status(HttpStatus.OK).body(studentHomeworks);
        }

        @GetMapping("/courses/{courseId}/subjects/{subjectId}/students/{studentId}/assistance/{month}/{year}")
        @Secured({ "ROLE_STUDENT", "ROLE_TUTOR" })
        public ResponseEntity<?> getStudentAssistanceInASubject(@PathVariable Integer courseId,
                        @PathVariable Integer subjectId, @PathVariable Long studentId, @PathVariable Integer month,
                        @PathVariable Integer year) {
                StudentWithNestedAssistancesDTO studentAssistance = studentAssistancesFinder
                                .getAssistancesOfStudentOfASubjectInACourse(courseId, subjectId, studentId, month,
                                                year);
                return ResponseEntity.status(HttpStatus.OK).body(studentAssistance);
        }

        @GetMapping("/schools/{id}/students")
        @Secured({ "ROLE_STUDENT", "ROLE_ADMIN" })
        public ResponseEntity<?> getSchoolStudents(@PathVariable Long id) {
                try {
                        List<StudentMiniProfileDTO> students = finder.findSchoolStudents(id);
                        return ResponseEntity.status(HttpStatus.OK).body(students);
                } catch (StudentNotFound e) {
                        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
                }
        }

        @GetMapping("/schools/{id}/students-edit/{studentId}")
        @Secured("ROLE_ADMIN")
        public ResponseEntity<?> getStudentWithEditableData(@PathVariable Long id, @PathVariable Long studentId) {
                try {
                        StudentEditableFieldsDTO student = finder.getStudentWithEditableFields(id, studentId);
                        return ResponseEntity.status(HttpStatus.OK).body(student);
                } catch (StudentNotFound e) {
                        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
                }
        }

        @GetMapping("/students/{id}/home")
        @Secured("ROLE_STUDENT")
        public ResponseEntity<?> getStudentHome(@PathVariable Long id) {
                try {
                        StudentHomeDTO studentHome = finder.findStudentHome(id);
                        return ResponseEntity.status(HttpStatus.OK).body(studentHome);
                } catch (StudentNotFound e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
                }
        }
    }



