package com.ufro.culmingapp.student.application.DTOs;

import com.ufro.culmingapp.shared.domain.valueobjects.FullName;

public class StudentWithFullNameDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;

    public StudentWithFullNameDTO(Long id, FullName fullName) {
        this.id = id;
        this.firstName = fullName.getFirstName();
        this.middleName = fullName.getMiddleName();
        this.lastName = fullName.getLastName();
        this.secondSurname = fullName.getSecondSurname();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }
}
