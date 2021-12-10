package com.ufro.culmingapp.course.application;

import java.util.Optional;

import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.CourseRepository;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;

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

    public Optional<Course> findStudentCourseByYear(Long studentId) {
        return repository.fetchStudentCourseByYear(studentId);
    }

}
