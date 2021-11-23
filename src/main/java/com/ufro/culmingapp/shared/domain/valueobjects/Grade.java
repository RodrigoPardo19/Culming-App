package com.ufro.culmingapp.shared.domain.valueobjects;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.GradeNotValid;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class Grade {

    private Double grade;

    public Grade() {
        //Used only for spring
    }

    public Grade(Double grade) throws NullFieldNotPermitted, GradeNotValid{
        if(isNull(grade)) {
            throw new NullFieldNotPermitted("Grade");
        }
        if(!isValid(grade)){
            throw new GradeNotValid();
        }
        this.grade = grade;
    }

    private Boolean isNull(Double grade) {
       return grade == null;
    }

    private Boolean isValid(Double grade) {
        if (grade < 1.0 || grade > 7.0) {
            return false;
        }
        return true;
    }

    public Double getGrade() {
        return this.grade;
    }
}
