package com.ufro.culmingapp.homework.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ufro.culmingapp.studenthomework.domain.StudentHomework;

@Entity
@Table(name = "homework_states")
public class HomeworkState {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "state")
    private List<StudentHomework> homeworks = new ArrayList<>();

    public HomeworkState() {
        // Used only for spring
    }

    public HomeworkState(String name) {
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

    public List<StudentHomework> getHomeworks() {
        return this.homeworks;
    }

    public void setHomeworks(List<StudentHomework> homeworks) {
        this.homeworks = homeworks;
    }

}
