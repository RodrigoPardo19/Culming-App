package com.ufro.culmingapp.course.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufro.culmingapp.school.domain.School;
import com.ufro.culmingapp.student.domain.Student;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Column(name = "year_of_generation")
    private Integer yearOfGeneration;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "students_courses", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
            @JoinColumn(name = "course_id") })
    private List<Student> students = new ArrayList<>();

    public Course() {
        //
    }

    // Constructor with not null values
    public Course(String name, Integer yearOfGeneration) {
        this.name = name;
        this.yearOfGeneration = yearOfGeneration;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfGeneration() {
        return this.yearOfGeneration;
    }

    public void setYearOfGeneration(Integer yearOfGeneration) {
        this.yearOfGeneration = yearOfGeneration;
    }

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
