package com.ufro.culmingapp.student.application.DTOs;

public class StudentDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;
    private String email;
    private String address;
    private String dateOfBirth;
    private String enrollmentDate;
    private Boolean isActive;
    private Integer courseId;
    private Long tutorId;
    private Integer year;

    public StudentDTO(Long id, String firstName, String middleName, String lastName, String secondSurname,
                      String email, String address, String dateOfBirth, String enrollmentDate, Boolean isActive,
                      Integer courseId, Long tutorId, Integer year) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondSurname = secondSurname;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.enrollmentDate = enrollmentDate;
        this.isActive = isActive;
        this.courseId = courseId;
        this.tutorId = tutorId;
        this.year = year;
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

    public String getEmail() {
        return email;
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

    public Boolean isActive() {
        return isActive;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Long getTutorId() {
        return tutorId;
    }

    public Integer getYear() {
        return year;
    }
}
