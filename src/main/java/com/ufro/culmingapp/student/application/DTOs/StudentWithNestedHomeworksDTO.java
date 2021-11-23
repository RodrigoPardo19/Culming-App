package com.ufro.culmingapp.student.application.DTOs;

import java.util.HashSet;
import java.util.Set;

import com.ufro.culmingapp.homework.application.DTOs.HomeworkStatusDTO;

public class StudentWithNestedHomeworksDTO {

    private Long id;
    private String firstName;
    private String lastName;
    Set<HomeworkStatusDTO> states = new HashSet<>();

    public StudentWithNestedHomeworksDTO(Long id, String firstName, String lastName, Set<HomeworkStatusDTO> states) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.states = states;
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

    public Set<HomeworkStatusDTO> getStates() {
        return this.states;
    }

    public void setStates(Set<HomeworkStatusDTO> states) {
        this.states = states;
    }

}
