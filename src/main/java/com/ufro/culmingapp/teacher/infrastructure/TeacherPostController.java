package com.ufro.culmingapp.teacher.infrastructure;

import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.school.domain.exceptions.SchoolNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.exceptions.WrongEmailFormat;
import com.ufro.culmingapp.shared.domain.exceptions.WrongLength;
import com.ufro.culmingapp.shared.domain.valueobjects.*;
import com.ufro.culmingapp.subject.application.DTOs.SubjectWithCourseWithYearDTO;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherDTO;
import com.ufro.culmingapp.teacher.application.RegisterSubject;
import com.ufro.culmingapp.teacher.application.TeacherCreator;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class TeacherPostController {

    @Autowired
    private TeacherCreator creator;

    @Autowired
    private RegisterSubject register;

    @PostMapping("/schools/{id}/teachers")
    public ResponseEntity<?> addNewTeacher(
            @PathVariable Long id, @RequestBody TeacherDTO newTeacher) {
        try {
            FullName fullName = new FullName(newTeacher.getFirstName(), newTeacher.getMiddleName(),
                    newTeacher.getLastName(), newTeacher.getSecondSurname());
            Email email = new Email(newTeacher.getEmail());
            Address address = new Address(newTeacher.getAddress());
            DateOfBirth dateOfBirth = new DateOfBirth(newTeacher.getDateOfBirth());
            Phone phone = new Phone(newTeacher.getPhone());
            EnrollmentDate enrollmentDate = new EnrollmentDate(newTeacher.getEnrollmentDate());
            Boolean isActive = newTeacher.isActive();

            TeacherDTO teacher = creator.create(fullName, email, address, dateOfBirth, phone, enrollmentDate,
                    isActive, id);

            return ResponseEntity.status(HttpStatus.CREATED).body(teacher);

        } catch (NullFieldNotPermitted | WrongEmailFormat | ParseException | WrongLength | SchoolNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/teachers/{id}/register-subjects")
    public ResponseEntity<?> registerSubjects(
            @PathVariable Long id, @RequestBody SubjectWithCourseWithYearDTO subjects) {
        try {
            GenerationYear year = new GenerationYear(subjects.getYear());

            register.register(id, subjects.getId(), subjects.getCourseIds(), year);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (NullFieldNotPermitted | TeacherNotFound | SubjectNotFound | CourseNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }
}
