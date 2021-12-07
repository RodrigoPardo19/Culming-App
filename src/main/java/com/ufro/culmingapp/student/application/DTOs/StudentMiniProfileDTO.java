package com.ufro.culmingapp.student.application.DTOs;

import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.Email;
import com.ufro.culmingapp.shared.domain.valueobjects.FullName;

public class StudentMiniProfileDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;
    private String address;
    private String email;

    public StudentMiniProfileDTO(Long id, FullName fullName, Address address, Email email) {
        this.id = id;
        this.firstName = fullName.getFirstName();
        this.middleName = fullName.getMiddleName();
        this.lastName = fullName.getLastName();
        this.secondSurname = fullName.getSecondSurname();
        this.address = address.getAddress();
        this.email = email.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
