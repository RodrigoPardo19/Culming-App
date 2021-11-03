package com.ufro.culmingapp.teacher.infrastructure;

import java.util.List;

import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.teacher.application.TeacherFinderService;
import com.ufro.culmingapp.teacher.application.TeacherMapper;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherProfileDTO;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;

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

    @Autowired
    private TeacherMapper mapper;

    @GetMapping("/teachers/{id}/profile")
    public ResponseEntity<?> getTeacherProfile(@PathVariable Long id) {
        try {
            Teacher teacher = finder.findById(id);
            TeacherProfileDTO profile = mapper.mapTeacherToTeacherProfile(teacher);
            return ResponseEntity.status(HttpStatus.OK).body(profile);
        } catch (TeacherNotFound e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/teachers/{id}/subjects")
    public ResponseEntity<?> getTeacherSubjects(@PathVariable Long id) {
        try {
            List<Subject> subjects = finder.getSubjects(id);
            return ResponseEntity.status(HttpStatus.OK).body(subjects);
        } catch (TeacherNotFound e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
