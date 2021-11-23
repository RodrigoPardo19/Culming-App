package com.ufro.culmingapp.teacher.domain;

import java.util.HashSet;
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
import com.ufro.culmingapp.school.domain.School;
import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.DateOfBirth;
import com.ufro.culmingapp.shared.domain.valueobjects.Email;
import com.ufro.culmingapp.shared.domain.valueobjects.EnrollmentDate;
import com.ufro.culmingapp.shared.domain.valueobjects.ExitDate;
import com.ufro.culmingapp.shared.domain.valueobjects.FullName;
import com.ufro.culmingapp.shared.domain.valueobjects.Password;
import com.ufro.culmingapp.shared.domain.valueobjects.Phone;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private FullName fullName;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Address address;

    @Embedded
    private DateOfBirth dateOfBirth;

    @Embedded
    private Phone phone;

    private String biography;

    private String photo;

    @Embedded
    private EnrollmentDate enrollmentDate;

    @Embedded
    private ExitDate exitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "teacher")
    private Set<CourseSubjectTeacher> subjects = new HashSet<>();

    public Teacher() {
        // Used only for spring
    }

    public Teacher(FullName name, Email email, Address address, DateOfBirth dateOfBirth,
            Phone phone,
            EnrollmentDate enrollmentDate) {
        this.fullName = name;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.enrollmentDate = enrollmentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public EnrollmentDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(EnrollmentDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public ExitDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(ExitDate exitDate) {
        this.exitDate = exitDate;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
        school.getTeachers().add(this);
    }

    public Set<CourseSubjectTeacher> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<CourseSubjectTeacher> subjects) {
        this.subjects = subjects;
    }

}
