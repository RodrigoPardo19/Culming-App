package com.ufro.culmingapp.subject.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ufro.culmingapp.assistance.domain.Assistance;
import com.ufro.culmingapp.grade.domain.Grade;
import com.ufro.culmingapp.homework.domain.Homework;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.teacher.domain.Teacher;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students = new ArrayList<>();

    @ManyToMany(mappedBy = "subjects")
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<Grade> grades = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<Assistance> assistances = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<Homework> homeworks = new ArrayList<>();

    public Subject() {
        //
    }

    // Constructor with not null values
    public Subject(String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return this.teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Grade> getGrades() {
        return this.grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Assistance> getAssistances() {
        return this.assistances;
    }

    public void setAssistances(List<Assistance> assistances) {
        this.assistances = assistances;
    }

    public List<Homework> getHomeworks() {
        return this.homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

}
