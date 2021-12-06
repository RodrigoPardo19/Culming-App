package com.ufro.culmingapp.subject.application.DTOs;

import java.util.HashSet;
import java.util.Set;

public class SubjectWithCourseWithYearDTO {

    private Integer id;
    private Set<Integer> courseIds;
    private Integer year;

    public SubjectWithCourseWithYearDTO(Integer id, Set<Integer> courseIds, Integer year) {
        this.id = id;
        this.courseIds = new HashSet<>(courseIds);
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(Set<Integer> courseIds) {
        this.courseIds = courseIds;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
