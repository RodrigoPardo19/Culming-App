package com.ufro.culmingapp.subject.domain;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class SubjectName {

    private String name;

    public SubjectName() {
        // Used only for spring
    }

    public SubjectName(String name) throws NullFieldNotPermitted {
        if (isNull(name)) {
            throw new NullFieldNotPermitted("Name");
        }
        this.name = name;
    }

    private Boolean isNull(String value) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return false;
    }

    public String getName() {
        return this.name;
    }
}
