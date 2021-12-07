package com.ufro.culmingapp.tutor.application.DTOs;

public class TutorDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;
    private Integer age;
    private String email;
    private String address;

    public TutorDTO(Long id, String firstName, String middleName, String lastName, String secondSurname, Integer age,
                    String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondSurname = secondSurname;
        this.age = age;
        this.email = email;
        this.address = address;
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

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
