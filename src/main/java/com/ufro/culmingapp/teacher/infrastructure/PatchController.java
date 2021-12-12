package com.ufro.culmingapp.teacher.infrastructure;

import com.ufro.culmingapp.shared.application.EmailManagerService;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.exceptions.WrongLength;
import com.ufro.culmingapp.shared.domain.valueobjects.*;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherProfileDTO;
import com.ufro.culmingapp.teacher.application.TeacherFinderService;
import com.ufro.culmingapp.teacher.application.TeacherMapper;
import com.ufro.culmingapp.teacher.application.TeacherRemover;
import com.ufro.culmingapp.teacher.application.TeacherUpdaterService;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class PatchController {

    @Autowired
    private TeacherUpdaterService updater;

    @Autowired
    private TeacherMapper mapper;

    @Autowired
    private EmailManagerService sender;

    @Autowired
    private TeacherFinderService finder;

    @Autowired
    private TeacherRemover remover;

    @PatchMapping("/teachers/{id}/profile")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<?> updateTeacherProfile(@PathVariable Long id,
            @RequestBody TeacherProfileDTO newTeacherProfile) throws ParseException {
        try {
            Teacher teacher = updater.updateProfile(id,
                    new FullName(newTeacherProfile.getFirstName(), newTeacherProfile.getMiddleName(),
                            newTeacherProfile.getLastName(), newTeacherProfile.getSecondSurname()),
                    new Address(newTeacherProfile.getAddress()), new Phone(newTeacherProfile.getPhone()),
                    new DateOfBirth(newTeacherProfile.getDateOfBirth()), newTeacherProfile.getBiography());
            TeacherProfileDTO profile = finder.findTeacherProfile(id);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (NullFieldNotPermitted | WrongLength | NumberFormatException | DateTimeParseException
                | TeacherNotFound e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/teachers/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> changeTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherChanged)
            throws ParseException {
        try {
            FullName fullName = new FullName(teacherChanged.getFirstName(), teacherChanged.getMiddleName(),
                    teacherChanged.getLastName(), teacherChanged.getSecondSurname());
            Address address = new Address(teacherChanged.getAddress());
            DateOfBirth dateOfBirth = new DateOfBirth(teacherChanged.getDateOfBirth());
            Phone phone = new Phone(teacherChanged.getPhone());
            EnrollmentDate enrollmentDate = new EnrollmentDate(teacherChanged.getEnrollmentDate());
            TeacherDTO teacher = updater.update(id, fullName, address, dateOfBirth, phone, enrollmentDate);
            return ResponseEntity.status(HttpStatus.OK).body(teacher);
        } catch (NullFieldNotPermitted | WrongLength | DateTimeParseException | TeacherNotFound e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Implement with JWT
    @PatchMapping("/teacher/{id}/email")
    public void sendTestEmail(@PathVariable Long id) {
        this.sender.sendEmail("kurutadevelop03@gmail.com", "r.pardo03@ufromail.cl", "Buenas Noches",
                "Hola, que tal está la tarde ? :D");
    }

    @PatchMapping("/teachers/{id}/remove")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> removeTeacher(@PathVariable Long id) {
        try {
            remover.remove(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (TeacherNotFound e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
