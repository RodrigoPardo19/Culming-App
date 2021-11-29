package com.ufro.culmingapp.student.application.DTOs;

import com.ufro.culmingapp.homework.application.DTOs.HomeworkStatusDTO;

import java.util.ArrayList;
import java.util.List;

public class StudentWithNestedHomeworksDTO {

    private Long id;
    private String firstName;
    private String lastName;
    List<HomeworkStatusDTO> states = new ArrayList<>();

    public StudentWithNestedHomeworksDTO(Long id, String firstName, String lastName, List<HomeworkStatusDTO> states) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.states = new ArrayList<>(states);
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

    public List<HomeworkStatusDTO> getStates() {
        return this.states;
    }

    public void setStates(List<HomeworkStatusDTO> states) {
        this.states = states;
    }

}
