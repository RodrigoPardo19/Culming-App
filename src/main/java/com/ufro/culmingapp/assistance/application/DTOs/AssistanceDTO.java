package com.ufro.culmingapp.assistance.application.DTOs;

import com.ufro.culmingapp.assistance.domain.AssistanceDate;

public class AssistanceDTO {

    Long id;
    String date;

    public AssistanceDTO(Long id, AssistanceDate date) {
        this.id = id;
        this.date = date.getStringDate();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
