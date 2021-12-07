package com.ufro.culmingapp.studentsubject.domain;

import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.shared.domain.valueobjects.Grade;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.subject.domain.Subject;

import javax.persistence.*;

@Entity
@Table(name = "students_subjects")
public class StudentSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_subject_generator")
    @SequenceGenerator(name = "student_subject_generator", sequenceName = "seq_students_subjects", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Embedded
    private GenerationYear year;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Embedded
    @AttributeOverride(name = "grade", column = @Column(name = "final_grade"))
    private Grade finalGrade;

    public StudentSubject() {
        // Used only for spring
    }

    public StudentSubject(GenerationYear year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        student.getSubjects().add(this);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.getStudents().add(this);
    }

    public GenerationYear getYear() {
        return year;
    }

    public void setYear(GenerationYear year) {
        this.year = year;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Grade getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Grade finalGrade) {
        this.finalGrade = finalGrade;
    }

}
