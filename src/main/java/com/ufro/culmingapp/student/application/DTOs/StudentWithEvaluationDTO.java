package com.ufro.culmingapp.student.application.DTOs;

public class StudentWithEvaluationDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Long evaluationId;
    private Double grade;

    public StudentWithEvaluationDTO(Long id, String firstName, String lastName,
            Long evaluationId, Double grade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.evaluationId = evaluationId;
        this.grade = grade;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

}
