package com.ufro.culmingapp.student.application.DTOs;

import java.util.Set;

import com.ufro.culmingapp.shared.domain.valueobjects.GradeDTO;

public class StudentWithNestedEvaluationsDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Set<GradeDTO> grades;

    public StudentWithNestedEvaluationsDTO(Long id, String firstName, String lastName,
            Set<GradeDTO> grades) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grades = grades;
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

    public Set<GradeDTO> getGrades() {
        return grades;
    }

    public void setGrades(Set<GradeDTO> grades) {
        this.grades = grades;
    }

}
