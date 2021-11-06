package com.ufro.culmingapp.course.application;

import com.ufro.culmingapp.course.domain.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseFinderService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseFinderService(CourseRepository repository) {
        this.courseRepository = repository;
    }

}
