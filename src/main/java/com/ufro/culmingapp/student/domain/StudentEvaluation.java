package com.ufro.culmingapp.student.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ufro.culmingapp.evaluation.domain.Evaluation;
import com.ufro.culmingapp.shared.domain.valueobjects.Grade;

@Entity
@Table(name = "students_evaluations")
public class StudentEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_evaluation_generator")
    @SequenceGenerator(name = "student_evaluation_generator", sequenceName = "seq_students_evaluations", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    @Embedded
    private Grade grade;

    public StudentEvaluation() {
        // Used only for spring
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
        student.getEvaluations().add(this);
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
        evaluation.getStudents().add(this);
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

}
