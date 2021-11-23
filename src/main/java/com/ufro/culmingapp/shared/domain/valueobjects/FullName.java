package com.ufro.culmingapp.shared.domain.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class FullName {

    @Column(name = "name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "second_surname")
    private String secondSurname;

    public FullName() {
        // Used only for spring
    }

    public FullName(String firstName, String middleName, String lastName, String secondSurname)
            throws NullFieldNotPermitted {
        if (!isValid(firstName)) {
            throw new NullFieldNotPermitted("firstname");
        }
        if (!isValid(lastName)) {
            throw new NullFieldNotPermitted("lastname");
        }
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondSurname = secondSurname;
    }

    private Boolean isValid(String value) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return true;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

}
