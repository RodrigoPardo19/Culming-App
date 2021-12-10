package com.ufro.culmingapp.Role.domain;

import com.ufro.culmingapp.administrator.domain.Administrator;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.tutor.domain.Tutor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authorities")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "seq_authorities", allocationSize = 1)
    private Integer id;

    private String authority;

    @OneToMany(mappedBy = "role")
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<Tutor> tutors = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<Administrator> admins = new ArrayList<>();

    public Role() {
        // Used only for spring
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Tutor> getTutors() {
        return this.tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }

    public List<Administrator> getAdmins() {
        return this.admins;
    }

    public void setAdmins(List<Administrator> admins) {
        this.admins = admins;
    }

}
