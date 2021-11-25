package com.ufro.culmingapp.homework.application.DTOs;

public class HomeworkWithSubjectWithCourseDTO {
    
    private Long id;
    private String instruction;
    private String deadline;
    private Integer subjectId;
    private Integer courseId;

    public HomeworkWithSubjectWithCourseDTO(Long id, String instruction, String deadline,
     Integer subjectId, Integer courseId) {
        this.id = id;
        this.instruction = instruction;
        this.deadline = deadline;
        this.subjectId = subjectId;
        this.courseId = courseId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstruction() {
        return this.instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

}
