package com.ufro.culmingapp.evaluation.application.DTOs;

public class EvaluationWithSubjectWithCourseWithTypeDTO {

    private String description;
    private String date;
    private Integer subjectId;
    private Integer courseId;
    private Integer typeId;

    public EvaluationWithSubjectWithCourseWithTypeDTO(String description, String date,
            Integer subjectId, Integer courseId, Integer typeId) {
        this.description = description;
        this.date = date;
        this.subjectId = subjectId;
        this.courseId = courseId;
        this.typeId = typeId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Integer getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

}
