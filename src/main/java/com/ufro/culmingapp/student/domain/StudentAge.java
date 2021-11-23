package com.ufro.culmingapp.student.domain;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.AgeNotAccepted;

@Embeddable
public class StudentAge {

    private Integer age;

    public StudentAge(){
        // Used only for spring
    }

    public StudentAge(Integer age) throws AgeNotAccepted {
        if (!isValid(age)) {
            throw new AgeNotAccepted(3, 29);
        }
        this.age = age;
    }

    private Boolean isValid(Integer age) {
        if (age < 3 || age >= 30) {
            return false;
        }
        return true;
    }

    public Integer getAge() {
        return this.age;
    }

}
