package com.ufro.culmingapp.homework.domain.exceptions;

public class HomeworkStateNotFound extends Exception {

    public HomeworkStateNotFound(Integer id) {
        super("Homework state has not been found: " + id);
    }
    
}
