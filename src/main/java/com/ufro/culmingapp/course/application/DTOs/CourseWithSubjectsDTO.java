package com.ufro.culmingapp.course.application.DTOs;

import java.util.Set;

import com.ufro.culmingapp.subject.application.DTOs.SubjectDTO;

public class CourseWithSubjectsDTO {

    private Integer id;
    private String level;
    private Set<SubjectDTO> subjects;

    public CourseWithSubjectsDTO(Integer id, String level, Set<SubjectDTO> subjects) {
        this.id = id;
        this.level = level;
        this.subjects = subjects;
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

    public Set<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectDTO> subjects) {
        this.subjects = subjects;
    }

}
