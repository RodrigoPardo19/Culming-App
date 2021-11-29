package com.ufro.culmingapp.assistance.domain;

import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.student.domain.StudentAssistance;
import com.ufro.culmingapp.subject.domain.Subject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assistances")
public class Assistance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assistance_generator")
    @SequenceGenerator(name = "assistance_generator", sequenceName = "seq_assistances", allocationSize = 1)
    private Long id;

    @Embedded
    private AssistanceDate date;

    @Embedded
    private GenerationYear year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "assistance")
    private Set<StudentAssistance> students = new HashSet<>();

    public Assistance() {
        // Used only for spring
    }

    public Assistance(AssistanceDate date, GenerationYear year) {
        this.date = date;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssistanceDate getDate() {
        return date;
    }

    public void setDate(AssistanceDate date) {
        this.date = date;
    }

    public GenerationYear getYear() {
        return year;
    }

    public void setYear(GenerationYear year) {
        this.year = year;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.getAssistances().add(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        course.getAssistances().add(this);
    }

    public Set<StudentAssistance> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentAssistance> students) {
        this.students = students;
    }

}
