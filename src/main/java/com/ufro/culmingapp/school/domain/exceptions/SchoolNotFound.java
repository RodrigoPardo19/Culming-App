package com.ufro.culmingapp.school.domain.exceptions;

public class SchoolNotFound extends Exception {

    public SchoolNotFound(Long id) {
        super("School has not been found:" + id);
    }
}
