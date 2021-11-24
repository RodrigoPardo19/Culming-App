package com.ufro.culmingapp.student.application.DTOs;

import java.util.HashSet;
import java.util.Set;

import com.ufro.culmingapp.assistance.application.DTOs.AssistancesStatusDTO;

public class StudentWithNestedAssistancesDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private Set<AssistancesStatusDTO> states = new HashSet<>(); 

    public StudentWithNestedAssistancesDTO(Long id, String firstName, String lastName,
     Set<AssistancesStatusDTO> states) {
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

    public Set<AssistancesStatusDTO> getStates() {
        return this.states;
    }

    public void setStates(Set<AssistancesStatusDTO> states) {
        this.states = states;
    }

}
