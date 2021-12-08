package com.ufro.culmingapp.teacher.application.DTOs;

import com.ufro.culmingapp.shared.domain.valueobjects.*;

public class TeacherWithoutEmailAndActivityDTO {


    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;
    private String address;
    private String dateOfBirth;
    private String phone;
    private String enrollmentDate;

    public TeacherWithoutEmailAndActivityDTO(Long id, FullName fullName, Address address, DateOfBirth dateOfBirth,
                                             Phone phone, EnrollmentDate enrollmentDate) {
        this.id = id;
        this.firstName = fullName.getFirstName();
        this.middleName = fullName.getMiddleName();
        this.lastName = fullName.getLastName();
        this.secondSurname = fullName.getSecondSurname();
        this.address = address.getAddress();
        this.dateOfBirth = dateOfBirth.getStringDateOfBirth();
        this.phone = phone.getPhone();
        this.enrollmentDate = enrollmentDate.getStringEnrollmentDate();
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }
}
