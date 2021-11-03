package com.ufro.culmingapp.shared.domain.exceptions;

import java.util.List;

public class NullFieldNotPermitted extends Exception {

    public NullFieldNotPermitted(String field) {
        super("Null field is not permitted: " + field);
    }

    public NullFieldNotPermitted(List<String> fields) {
        super("Null field is not permitted: " + fields);
    }

}
