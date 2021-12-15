package com.ufro.culmingapp.student.application.DTOs;

import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.DateOfBirth;
import com.ufro.culmingapp.shared.domain.valueobjects.EnrollmentDate;
import com.ufro.culmingapp.shared.domain.valueobjects.FullName;

public class StudentEditableFieldsDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;
    private String address;
    private String dateOfBirth;
    private String enrollmentDate;

    public StudentEditableFieldsDTO(Long id, FullName fullName, Address address, DateOfBirth dateOfBirth,
            EnrollmentDate enrollmentDate) {
        this.id = id;
        this.firstName = fullName.getFirstName();
        this.middleName = fullName.getMiddleName();
        this.lastName = fullName.getLastName();
        this.secondSurname = fullName.getSecondSurname();
        this.address = address.getAddress();
        this.dateOfBirth = dateOfBirth.getStringDateOfBirth();
        this.enrollmentDate = enrollmentDate.getStringEnrollmentDate();
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getSecondSurname() {
        return this.secondSurname;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getEnrollmentDate() {
        return this.enrollmentDate;
    }

}
