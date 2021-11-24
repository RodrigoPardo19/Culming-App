package com.ufro.culmingapp.student.application.DTOs;

public class StudentWithAssistanceDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Long assistanceId;
    private Boolean isPresent;

    public StudentWithAssistanceDTO(Long id, String firstName, String lastName,
     Long assistanceId, Boolean isPresent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.assistanceId = assistanceId;
        this.isPresent = isPresent;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAssistanceId() {
        return this.assistanceId;
    }

    public void setAssistanceId(Long assistanceId) {
        this.assistanceId = assistanceId;
    }

    public Boolean isPresent() {
        return this.isPresent;
    }

    public void setIsPresent(Boolean isPresent) {
        this.isPresent = isPresent;
    }

}
