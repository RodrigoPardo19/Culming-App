package com.ufro.culmingapp.tutor.infrastructure;

import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithFullNameDTO;
import com.ufro.culmingapp.tutor.application.TutorFinder;
import com.ufro.culmingapp.tutor.application.TutorWithFullNameDTO;
import com.ufro.culmingapp.tutor.domain.TutorNotFound;
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
public class TutorGetController {

    @Autowired
    private TutorFinder finder;

    @GetMapping("/tutors/{id}")
    public ResponseEntity<?> getTutor(@PathVariable Long id) {
        try {
            TutorWithFullNameDTO tutor = finder.getTutorWithFullName(id);
            return ResponseEntity.ok(tutor);
        } catch (TutorNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/tutors/{id}/students")
    public ResponseEntity<?> getPupils(@PathVariable Long id) {
        List<StudentWithFullNameDTO> pupils = finder.getPupils(id);
        return ResponseEntity.ok(pupils);
    }
}
