package com.ufro.culmingapp.evaluation.domain;

import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.evaluationtype.domain.EvaluationType;
import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.student.domain.StudentEvaluation;
import com.ufro.culmingapp.subject.domain.Subject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "evaluations")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evaluation_generator")
    @SequenceGenerator(name = "evaluation_generator", sequenceName = "seq_evaluations", allocationSize = 1)
    private Long id;

    @Embedded
    private EvaluationDescription description;

    @Embedded
    private EvaluationDate date;

    @Embedded
    private GenerationYear year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private EvaluationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL)
    private Set<StudentEvaluation> students = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Evaluation() {
        // Used only for spring
    }

    public Evaluation(EvaluationDescription description, EvaluationDate date, GenerationYear year) {
        this.description = description;
        this.date = date;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluationDescription getDescription() {
        return description;
    }

    public void setDescription(EvaluationDescription description) {
        this.description = description;
    }

    public EvaluationDate getDate() {
        return date;
    }

    public void setDate(EvaluationDate date) {
        this.date = date;
    }

    public GenerationYear getYear() {
        return year;
    }

    public void setYear(GenerationYear year) {
        this.year = year;
    }

    public EvaluationType getType() {
        return type;
    }

    public void setType(EvaluationType type) {
        this.type = type;
        type.getEvaluations().add(this);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.getEvaluations().add(this);
    }

    public Set<StudentEvaluation> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentEvaluation> students) {
        this.students = students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        course.getEvaluations().add(this);
    }

}
