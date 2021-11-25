package com.ufro.culmingapp.subject.domain.exceptions;

public class SubjectNotFound extends Exception {

    public SubjectNotFound(Integer id) {
        super("Subject has not been found: " + id);
    }
    
}
