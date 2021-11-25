package com.ufro.culmingapp.homework.domain.exceptions;

public class HomeworkNotFound extends Exception {

    public HomeworkNotFound(Long id) {
        super("Homework has not been found: " + id);
    }
    
}
