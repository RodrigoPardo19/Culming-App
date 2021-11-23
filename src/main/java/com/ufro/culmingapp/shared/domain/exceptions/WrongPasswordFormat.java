package com.ufro.culmingapp.shared.domain.exceptions;

public class WrongPasswordFormat extends Exception {

    public WrongPasswordFormat(String password) {
        super("Wrong password format: " + password);
    }
}
