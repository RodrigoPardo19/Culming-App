package com.ufro.culmingapp.school.domain;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class SchoolName {

    private String name;

    public SchoolName() {
        // Used only for spring
    }

    public SchoolName(String name) throws NullFieldNotPermitted {
        if (isNull(name)) {
            throw new NullFieldNotPermitted("Name");
        }
        this.name = name;
    }

    private Boolean isNull(String name) {
        if (name == null || name.isBlank()) {
            return true;
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

}
