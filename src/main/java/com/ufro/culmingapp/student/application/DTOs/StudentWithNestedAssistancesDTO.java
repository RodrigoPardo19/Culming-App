package com.ufro.culmingapp.student.application.DTOs;

import com.ufro.culmingapp.assistance.application.DTOs.AssistancesStatusDTO;

import java.util.ArrayList;
import java.util.List;

public class StudentWithNestedAssistancesDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private List<AssistancesStatusDTO> states = new ArrayList<>();

    public StudentWithNestedAssistancesDTO(Long id, String firstName, String lastName,
                                           List<AssistancesStatusDTO> states) {
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

    public List<AssistancesStatusDTO> getStates() {
        return this.states;
    }

    public void setStates(List<AssistancesStatusDTO> states) {
        this.states = states;
    }

}
