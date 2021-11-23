package com.ufro.culmingapp.school.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ufro.culmingapp.administrator.domain.Administrator;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.Email;
import com.ufro.culmingapp.shared.domain.valueobjects.Phone;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.teacher.domain.Teacher;

@Entity
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private SchoolName name;

    @Embedded
    private Address address;

    @Embedded
    private Email email;

    @Embedded
    private Phone phone;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Administrator> administrators = new ArrayList<>();

    @OneToMany(mappedBy = "school")
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "school")
    private List<Student> students = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "courses_schools", joinColumns = {
            @JoinColumn(name = "school_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "course_id") })
    private Set<Course> courses = new HashSet<>();

    public School() {
        // Used only for spring
    }

    public School(SchoolName name, Address address, Email email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SchoolName getName() {
        return name;
    }

    public void setName(SchoolName name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public List<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<Administrator> administrators) {
        this.administrators = administrators;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}
