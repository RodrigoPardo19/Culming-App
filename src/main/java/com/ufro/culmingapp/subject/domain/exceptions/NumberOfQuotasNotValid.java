package com.ufro.culmingapp.subject.domain.exceptions;

public class NumberOfQuotasNotValid extends Exception {

    public NumberOfQuotasNotValid() {
        super("Number of quotas must be beetween 1 and 150");
    }

}
