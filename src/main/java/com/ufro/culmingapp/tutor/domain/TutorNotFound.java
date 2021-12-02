package com.ufro.culmingapp.tutor.domain;

public class TutorNotFound extends Exception {

    public TutorNotFound(Long id) {
        super("Tutor has not been found: " + id);
    }
}
