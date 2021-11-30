package com.ufro.culmingapp.subject.infrastructure;

import com.ufro.culmingapp.subject.application.DTOs.SubjectDTO;
import com.ufro.culmingapp.subject.application.SubjectFinderService;
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
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class SubjectGetController {

    @Autowired
    private SubjectFinderService finder;

    @GetMapping("/students/{studentId}/subjects")
    public ResponseEntity<?> getSubjectsTakenByAStudent(@PathVariable Long studentId) {

        List<SubjectDTO> subjects = finder.getSubjectsTakenByAStudent(studentId);

        return ResponseEntity.status(HttpStatus.OK).body(subjects);
    }
}

