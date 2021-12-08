package com.ufro.culmingapp.student.application.DTOs;

public class StudentWithoutCourseAndTutorDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;
    private String address;
    private String dateOfBirth;
    private String enrollmentDate;

    public StudentWithoutCourseAndTutorDTO(Long id, String firstName, String middleName, String lastName,
                                           String secondSurname, String address, String dateOfBirth,
                                           String enrollmentDate) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondSurname = secondSurname;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.enrollmentDate = enrollmentDate;
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

    public String getEnrollmentDate() {
        return enrollmentDate;
    }
}
