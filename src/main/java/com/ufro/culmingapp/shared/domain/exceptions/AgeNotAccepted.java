package com.ufro.culmingapp.shared.domain.exceptions;

public class AgeNotAccepted extends Exception {

    public AgeNotAccepted(Integer start, Integer end) {
        super("The age must be beetween " + start + " and " + end);
    }
}
