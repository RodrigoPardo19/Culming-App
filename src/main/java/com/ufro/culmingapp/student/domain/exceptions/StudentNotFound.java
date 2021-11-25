package com.ufro.culmingapp.student.domain.exceptions;

public class StudentNotFound extends Exception {
    
    public StudentNotFound(Long id) {
        super("Student has not been found " + id);
    }
}
