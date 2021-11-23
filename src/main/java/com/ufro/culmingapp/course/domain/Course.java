package com.ufro.culmingapp.course.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ufro.culmingapp.assistance.domain.Assistance;
import com.ufro.culmingapp.coursesubjectteacher.domain.CourseSubjectTeacher;
import com.ufro.culmingapp.evaluation.domain.Evaluation;
import com.ufro.culmingapp.homework.domain.Homework;
import com.ufro.culmingapp.studentcourse.domain.StudentCourse;
import com.ufro.culmingapp.school.domain.School;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String level;

    @ManyToMany(mappedBy = "courses")
    private Set<School> schools = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<StudentCourse> students = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<CourseSubjectTeacher> subjects = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private List<Evaluation> evaluations = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Homework> homeworks = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Assistance> assistances = new ArrayList<>();

    public Course() {
        // Used only for spring
    }

    public Course(String level) {
        this.level = level;
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

    public Set<School> getSchools() {
        return schools;
    }

    public void setSchools(Set<School> schools) {
        this.schools = schools;
    }

    public Set<StudentCourse> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentCourse> students) {
        this.students = students;
    }

    public Set<CourseSubjectTeacher> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<CourseSubjectTeacher> subjects) {
        this.subjects = subjects;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public List<Assistance> getAssistances() {
        return assistances;
    }

    public void setAssistances(List<Assistance> assistances) {
        this.assistances = assistances;
    }

}
