package com.ufro.culmingapp.homework.application.DTOs;

public class HomeworkStatusDTO {

    private Long id;
    private String status;

    public HomeworkStatusDTO(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
