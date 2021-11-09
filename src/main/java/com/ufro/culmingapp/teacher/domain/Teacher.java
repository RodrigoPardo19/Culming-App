package com.ufro.culmingapp.teacher.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.DateOfBirth;
import com.ufro.culmingapp.shared.domain.valueobjects.Email;
import com.ufro.culmingapp.shared.domain.valueobjects.Phone;
import com.ufro.culmingapp.subject.domain.Subject;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private TeacherName fullName;

    @Embedded
    private Email email;

    private String password;

    @Embedded
    private Address address;

    @Embedded
    private DateOfBirth dateOfBirth;

    @Embedded
    private Phone phone;

    private String biography;

    private String photo;

    @Column(name = "enrollment_date")
    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;

    @Column(name = "exit_date")
    @Temporal(TemporalType.DATE)
    private Date exitDate;

    @ManyToMany
    @JoinTable(name = "teachers_subjects", joinColumns = {
            @JoinColumn(name = "teacher_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "subject_id") })
    @JsonIgnore
    private List<Subject> subjects = new ArrayList<>();

    public Teacher() {
        // Used just for spring
    }

    public Teacher(TeacherName name, Email email, Address address, DateOfBirth dateOfBirth,
            Phone phone,
            Date enrollmentDate) {
        this.fullName = name;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.enrollmentDate = enrollmentDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeacherName getFullName() {
        return this.fullName;
    }

    public void updateName(TeacherName newName) {
        this.fullName = newName;
    }

    public Email getEmail() {
        return this.email;
    }

    public void updateEmail(Email email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return this.address;
    }

    public void updateAddress(Address address) {
        this.address = address;
    }

    public DateOfBirth getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void updateDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public void updatePhone(Phone phone) {
        this.phone = phone;
    }

    public String getBiography() {
        return this.biography;
    }

    public void updateBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Date getExitDate() {
        return this.exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public List<Subject> getSubjects() {
        return this.subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}
