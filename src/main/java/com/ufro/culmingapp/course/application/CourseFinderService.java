package com.ufro.culmingapp.course.application;

import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.course.application.DTOs.CourseDTO;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.CourseRepository;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.school.domain.exceptions.SchoolNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseFinderService {

    private CourseRepository repository;

    @Autowired
    public CourseFinderService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course findById(Integer id) throws CourseNotFound {
        Optional<Course> course = repository.findById(id);
        if (!course.isPresent()) {
            throw new CourseNotFound(id);
        }
        return course.get();
    }

    public List<CourseDTO> findAllSchoolCourses(Long schoolId) throws SchoolNotFound {
        Optional<List<CourseDTO>> courses = repository.fetchAllSchoolCourses(schoolId);
        if (courses.isEmpty()) {
            throw new SchoolNotFound(schoolId);
        }
        return courses.get();
    }

}
