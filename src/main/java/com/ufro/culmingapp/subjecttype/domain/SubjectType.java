package com.ufro.culmingapp.subjecttype.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ufro.culmingapp.subject.domain.Subject;

@Entity
@Table(name = "subject_types")
public class SubjectType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String type;

    @OneToMany(mappedBy = "type")
    private List<Subject> subjects = new ArrayList<>();

    public SubjectType() {
        // Used only for spring
    }

    public SubjectType(String type) {
        this.type = type;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.setType(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}
