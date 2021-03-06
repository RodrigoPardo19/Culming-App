package com.ufro.culmingapp.tutor.infrastructure;

import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithCourseAndFullnameDTO;
import com.ufro.culmingapp.tutor.application.TutorFinder;
import com.ufro.culmingapp.tutor.application.TutorWithFullNameDTO;
import com.ufro.culmingapp.tutor.application.DTOs.TutorHomeDTO;
import com.ufro.culmingapp.tutor.domain.TutorNotFound;
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
public class TutorGetController {

    @Autowired
    private TutorFinder finder;

    @GetMapping("/tutors/{id}")
    @Secured({ "ROLE_TUTOR", "ROLE_ADMIN" })
    public ResponseEntity<?> getTutor(@PathVariable Long id) {
        try {
            TutorWithFullNameDTO tutor = finder.getTutorWithFullName(id);
            return ResponseEntity.ok(tutor);
        } catch (TutorNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/schools/{id}/tutors")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getSchoolTutors(@PathVariable Long id) {
        try {
            List<TutorWithFullNameDTO> tutors = finder.getSchoolTutors(id);
            return ResponseEntity.ok(tutors);
        } catch (TutorNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/tutors/{id}/students")
    @Secured("ROLE_TUTOR")
    public ResponseEntity<?> getPupils(@PathVariable Long id) {
        List<StudentWithCourseAndFullnameDTO> pupils = finder.getPupils(id);
        return ResponseEntity.ok(pupils);
    }

    @GetMapping("/tutors/{id}/home")
    @Secured("ROLE_TUTOR")
    public ResponseEntity<?> getTutorHome(@PathVariable Long id) {
        try {
            TutorHomeDTO tutorHome = finder.findTutorHome(id);
            return ResponseEntity.status(HttpStatus.OK).body(tutorHome);
        } catch (TutorNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
        }
    }
}
