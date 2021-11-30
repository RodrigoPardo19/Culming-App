package com.ufro.culmingapp.tutor.infrastructure;

import com.ufro.culmingapp.student.application.DTOs.StudentWithFullNameDTO;
import com.ufro.culmingapp.tutor.application.TutorFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TutorGetController {

    @Autowired
    private TutorFinder finder;

    @GetMapping("/tutors/{id}/students")
    public ResponseEntity<?> getPupils(@PathVariable Long id) {
        List<StudentWithFullNameDTO> pupils = finder.getPupils(id);
        return ResponseEntity.ok(pupils);
    }
}
