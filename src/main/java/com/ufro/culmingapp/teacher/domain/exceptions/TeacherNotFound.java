package com.ufro.culmingapp.teacher.domain.exceptions;

public class TeacherNotFound extends Exception {

    public TeacherNotFound(Long id) {
        super("This teacher has not been found. id: " + id);
    }

    public TeacherNotFound(String email) {
        super("This teacher has not been found. email:" + email);
    }



}
