package com.ufro.culmingapp.teacher.infrastructure;

import java.util.List;

import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.teacher.application.TeacherFinderService;
import com.ufro.culmingapp.teacher.application.TeacherUpdaterService;
import com.ufro.culmingapp.teacher.domain.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {

    @Autowired
    private TeacherFinderService finder;

    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable Long id) {
        try {
            Teacher teacher = finder.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(teacher);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teachers/{id}/subjects")
    public ResponseEntity<?> getTeacherSubjects(@PathVariable Long id) {
        try {
            List<Subject> subjects = finder.getSubjects(id);
            return ResponseEntity.status(HttpStatus.OK).body(subjects);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
