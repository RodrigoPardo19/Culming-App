package com.ufro.culmingapp.student.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufro.culmingapp.school.domain.School;
import com.ufro.culmingapp.shared.domain.valueobjects.*;
import com.ufro.culmingapp.studentcourse.domain.StudentCourse;
import com.ufro.culmingapp.studentsubject.domain.StudentSubject;
import com.ufro.culmingapp.tutor.domain.Tutor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private FullName fullName;

    @Embedded
    private StudentAge age;

    @Embedded
    private DateOfBirth dateOfBirth;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Address address;

    @Embedded
    private StudentGraduationDate graduationDate;

    @Embedded
    private ExitDate exitDate;

    @Embedded
    private EnrollmentDate enrollmentDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<StudentCourse> courses = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<StudentSubject> subjects = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<StudentEvaluation> evaluations = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<StudentHomework> homeworks = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<StudentAssistance> assistances = new HashSet<>();

    public Student() {
        // Used only for spring
    }

    public Student(FullName fullName, DateOfBirth dateOfBirth, Email email, Address address,
                   EnrollmentDate enrollmentDate,
                   Boolean isActive) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.enrollmentDate = enrollmentDate;
        this.isActive = isActive;
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

    public StudentAge getAge() {
        return age;
    }

    public void setAge(StudentAge age) {
        this.age = age;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public StudentGraduationDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(StudentGraduationDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public ExitDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(ExitDate exitDate) {
        this.exitDate = exitDate;
    }

    public EnrollmentDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(EnrollmentDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
        school.getStudents().add(this);
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
        tutor.getStudents().add(this);
    }

    public Set<StudentCourse> getCourses() {
        return courses;
    }

    public void setCourses(Set<StudentCourse> courses) {
        this.courses = courses;
    }

    public Set<StudentSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<StudentSubject> subjects) {
        this.subjects = subjects;
    }

    public Set<StudentEvaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<StudentEvaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Set<StudentHomework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(Set<StudentHomework> homeworks) {
        this.homeworks = homeworks;
    }

    public Set<StudentAssistance> getAssistances() {
        return assistances;
    }

    public void setAssistances(Set<StudentAssistance> assistances) {
        this.assistances = assistances;
    }

}
