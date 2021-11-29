package com.ufro.culmingapp.student.application.DTOs;

public class StudentAssistanceDTO {

    private Long id;
    private Boolean isPresent;

    public StudentAssistanceDTO(Long id, Boolean isPresent) {
        this.id = id;
        this.isPresent = isPresent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }
}
