package com.ufro.culmingapp.shared.domain.exceptions;

public class GradeNotValid extends Exception {

    public GradeNotValid() {
        super("The grade must be beetween 1.0 and 7.0");
    }
}
