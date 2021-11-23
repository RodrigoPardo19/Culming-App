package com.ufro.culmingapp.student.application.DTOs;

import com.ufro.culmingapp.homework.domain.HomeworkDeadline;

public class StudentWithHomeworkDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Long homeworkId;
    private String homeworkIntruction;
    private String homeworkDeadline;
    private String homeworkStatus;

    public StudentWithHomeworkDTO(Long id, String firstName, String lastName, Long homeworkId,
            String homeworkIntruction, HomeworkDeadline homeworkDeadline, String homeworkStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeworkId = homeworkId;
        this.homeworkIntruction = homeworkIntruction;
        this.homeworkDeadline = homeworkDeadline.getStringDeadLine();
        this.homeworkStatus = homeworkStatus;
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

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkIntruction() {
        return homeworkIntruction;
    }

    public void setHomeworkIntruction(String homeworkIntruction) {
        this.homeworkIntruction = homeworkIntruction;
    }

    public String getHomeworkDeadline() {
        return homeworkDeadline;
    }

    public void setHomeworkDeadline(String homeworkDeadline) {
        this.homeworkDeadline = homeworkDeadline;
    }

    public String getHomeworkStatus() {
        return homeworkStatus;
    }

    public void setHomeworkStatus(String homeworkStatus) {
        this.homeworkStatus = homeworkStatus;
    }

}
