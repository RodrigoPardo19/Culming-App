package com.ufro.culmingapp.studentassistance.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.ufro.culmingapp.assistance.domain.Assistance;
import com.ufro.culmingapp.student.domain.Student;

@Entity
@Table(name = "students_assistances")
public class StudentAssistance {

    @EmbeddedId
    private StudentAssistanceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("assistanceId")
    @JoinColumn(name = "assistance_id")
    private Assistance assistance;

    @Column(name = "is_present")
    private Boolean isPresent;

    public StudentAssistance() {
        // Used only for spring
    }

    public StudentAssistance(Student student, Assistance assistance, Boolean isPresent) {
        this.id = new StudentAssistanceId(student.getId(), assistance.getId());
        this.student = student;
        this.assistance = assistance;
        this.isPresent = isPresent;
        this.student.getAssistances().add(this);
        this.assistance.getStudents().add(this);
    }

    public StudentAssistanceId getId() {
        return id;
    }

    public void setId(StudentAssistanceId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assistance getAssistance() {
        return assistance;
    }

    public void setAssistance(Assistance assistance) {
        this.assistance = assistance;
    }

    public Boolean getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Boolean isPresent) {
        this.isPresent = isPresent;
    }

}
