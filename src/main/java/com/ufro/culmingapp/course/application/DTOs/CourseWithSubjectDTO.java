package com.ufro.culmingapp.course.application.DTOs;

public class CourseWithSubjectDTO {

    private Integer id;
    private String level;
    private Integer subjectId;
    private String subjectName;

    public CourseWithSubjectDTO(Integer id, String level, Integer subjectId, String subjectName) {
        this.id = id;
        this.level = level;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
