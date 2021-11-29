package com.ufro.culmingapp.assistance.application.DTOs;

public class AssistanceWithCourseWithSubjectDTO {

    private String date;
    private Integer courseId;
    private Integer subjectId;

    public AssistanceWithCourseWithSubjectDTO(String date, Integer courseId, Integer subjectId) {
        this.date = date;
        this.courseId = courseId;
        this.subjectId = subjectId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
