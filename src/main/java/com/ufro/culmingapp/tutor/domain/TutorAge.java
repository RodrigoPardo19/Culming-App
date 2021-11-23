package com.ufro.culmingapp.tutor.domain;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.AgeNotAccepted;

@Embeddable
public class TutorAge {

    private Integer age;

    public TutorAge() {
        // Used only for spring
    }

    public TutorAge(Integer age) throws AgeNotAccepted {
        if (!isValid(age)) {
            throw new AgeNotAccepted(18, 99);
        }
        this.age = age;
    }

    private Boolean isValid(Integer age) {
        if (age < 18 || age > 99) {
            return false;
        }
        return true;
    }

    public Integer getAge() {
        return this.age;
    }

}
