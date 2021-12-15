package com.ufro.culmingapp.administrator.domain.exceptions;

public class AdminNotFound extends Exception {

    public AdminNotFound(Long id) {
        super("Admin has not been found: " + id);
    }

}
