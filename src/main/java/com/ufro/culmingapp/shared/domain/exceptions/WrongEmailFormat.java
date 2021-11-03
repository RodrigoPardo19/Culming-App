package com.ufro.culmingapp.shared.domain.exceptions;

public class WrongEmailFormat extends Exception {

    public WrongEmailFormat(String email) {
        super("Wrong email format" + email);
    }
}
