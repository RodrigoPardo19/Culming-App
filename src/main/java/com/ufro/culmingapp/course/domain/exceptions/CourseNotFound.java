package com.ufro.culmingapp.course.domain.exceptions;

public class CourseNotFound extends Exception {
    
    public CourseNotFound(Integer id) {
        super("Course has not been found " + id);
    }
}
