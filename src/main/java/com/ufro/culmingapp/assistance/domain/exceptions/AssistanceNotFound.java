package com.ufro.culmingapp.assistance.domain.exceptions;

public class AssistanceNotFound extends Exception {

    public AssistanceNotFound(Long id) {
        super("Assistance has not been found: " + id);
    }
}
