package com.ufro.culmingapp.subject.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ufro.culmingapp.coursesubjectteacher.domain.CourseSubjectTeacher;
import com.ufro.culmingapp.studentsubject.domain.StudentSubject;
import com.ufro.culmingapp.subjecttype.domain.SubjectType;
import com.ufro.culmingapp.assistance.domain.Assistance;
import com.ufro.culmingapp.evaluation.domain.Evaluation;
import com.ufro.culmingapp.homework.domain.Homework;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Embedded
    private SubjectName name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_type_id")
    private SubjectType type;

    @Embedded
    private SubjectQuota quotas;

    @OneToMany(mappedBy = "subject")
    private Set<CourseSubjectTeacher> courses = new HashSet<>();

    @OneToMany(mappedBy = "subject")
    private Set<StudentSubject> students = new HashSet<>();

    @OneToMany(mappedBy = "subject")
    private List<Evaluation> evaluations = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<Assistance> assistances = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<Homework> homeworks = new ArrayList<>();

    public Subject() {
        // Used only for spring
    }

    public Subject(SubjectName name) {
        this.name = name;
    }

    public Subject(SubjectName name, SubjectQuota quotas) {
        this.name = name;
        this.quotas = quotas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SubjectName getName() {
        return name;
    }

    public void setName(SubjectName name) {
        this.name = name;
    }

    public SubjectType getType() {
        return type;
    }

    public void setType(SubjectType type) {
        this.type = type;
        type.getSubjects().add(this);
    }

    public SubjectQuota getQuotas() {
        return quotas;
    }

    public void setQuotas(SubjectQuota quotas) {
        this.quotas = quotas;
    }

    public Set<CourseSubjectTeacher> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseSubjectTeacher> courses) {
        this.courses = courses;
    }

    public Set<StudentSubject> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentSubject> students) {
        this.students = students;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public List<Assistance> getAssistances() {
        return assistances;
    }

    public void setAssistances(List<Assistance> assistances) {
        this.assistances = assistances;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

}
