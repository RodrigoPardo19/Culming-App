package com.ufro.culmingapp.student.infrastructure;

import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.evaluation.domain.exceptions.EvaluationNotFound;
import com.ufro.culmingapp.school.domain.exceptions.SchoolNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.GradeNotValid;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.exceptions.WrongEmailFormat;
import com.ufro.culmingapp.shared.domain.valueobjects.*;
import com.ufro.culmingapp.student.application.DTOs.StudentDTO;
import com.ufro.culmingapp.student.application.StudentCreator;
import com.ufro.culmingapp.student.application.StudentQualifier;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import com.ufro.culmingapp.tutor.domain.TutorNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class StudenPostController {

    @Autowired
    private StudentQualifier qualifier;

    @Autowired
    private StudentCreator creator;

    @PostMapping("/students/{studentId}/evaluations/{evaluationId}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<?> rateStudent(@PathVariable Long studentId, @PathVariable Long evaluationId,
            @RequestBody GradeDTO grade) {
        try {
            Grade newGrade = new Grade(grade.getGrade());
            qualifier.rate(studentId, evaluationId, newGrade);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NullFieldNotPermitted | GradeNotValid | StudentNotFound | EvaluationNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/schools/{id}/students")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> addNewStudent(
            @PathVariable Long id, @RequestBody StudentDTO newStudent) {
        try {
            FullName fullName = new FullName(newStudent.getFirstName(), newStudent.getMiddleName(),
                    newStudent.getLastName(), newStudent.getSecondSurname());
            Email email = new Email(newStudent.getEmail());
            Address address = new Address(newStudent.getAddress());
            DateOfBirth dateOfBirth = new DateOfBirth(newStudent.getDateOfBirth());
            EnrollmentDate enrollmentDate = new EnrollmentDate(newStudent.getEnrollmentDate());
            Boolean isActive = newStudent.isActive();
            Integer courseId = newStudent.getCourseId();
            Long tutorId = newStudent.getTutorId();
            GenerationYear year = new GenerationYear(newStudent.getYear());
            StudentDTO student = creator.create(fullName, email, address, dateOfBirth, enrollmentDate,
                    isActive, id, courseId, tutorId, year);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        } catch (NullFieldNotPermitted | WrongEmailFormat | ParseException | SchoolNotFound | TutorNotFound
                | CourseNotFound | StudentNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }
}
