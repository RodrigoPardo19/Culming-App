package com.ufro.culmingapp.student.application.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.ufro.culmingapp.shared.domain.valueobjects.GradeDTO;

public class StudentWithNestedEvaluationsDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private List<GradeDTO> grades;

    public StudentWithNestedEvaluationsDTO(Long id, String firstName, String lastName,
            List<GradeDTO> grades) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grades = new ArrayList<>(grades);
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

    public List<GradeDTO> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeDTO> grades) {
        this.grades = grades;
    }

}
