package com.ufro.culmingapp.homework.application.DTOs;

import com.ufro.culmingapp.homework.domain.HomeworkDeadline;

public class HomeworkDTO {

    private Long id;
    private String instruction;
    private String deadline;

    public HomeworkDTO(Long id, String instruction, HomeworkDeadline deadline) {
        this.id = id;
        this.instruction = instruction;
        this.deadline = deadline.getStringDeadLine();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

}
