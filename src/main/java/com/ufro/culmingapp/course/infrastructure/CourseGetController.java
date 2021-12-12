package com.ufro.culmingapp.course.infrastructure;

import java.util.List;

import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.application.DTOs.CourseDTO;
import com.ufro.culmingapp.school.domain.exceptions.SchoolNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseGetController {

    @Autowired
    private CourseFinderService finder;

    @GetMapping("/schools/{id}/courses")
    public ResponseEntity<?> getAllSchoolCourses(@PathVariable Long id) {
        try {
            List<CourseDTO> courses = finder.findAllSchoolCourses(id);
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        } catch (SchoolNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }

}
