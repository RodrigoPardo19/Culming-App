package com.ufro.culmingapp.shared.domain.valueobjects;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class GenerationYear {

    private Integer year;

    public GenerationYear() {
        // Used only for spring
    }

    public GenerationYear(Integer year) throws NullFieldNotPermitted {
        if(isNull(year)) {
            throw new NullFieldNotPermitted("Year");
        }
        this.year = year;
    }

    private Boolean isNull(Integer year) {
        return year == null;
    }

    public Integer getYear() {
        return this.year;
    }

}
