package com.ufro.culmingapp.course.application.DTOs;

public class CourseDTO {

    private Integer id;
    private String level;

    public CourseDTO(Integer id, String level) {
        this.id = id;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

}
