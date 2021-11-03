package com.ufro.culmingapp.shared.domain.exceptions;

public class WrongLength extends Exception {

    public WrongLength(String field) {
        super("Wrong length for field: " + field);
    }
}
