package com.ufro.culmingapp.homework.domain;

import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.student.domain.StudentHomework;
import com.ufro.culmingapp.subject.domain.Subject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "homeworks")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "homework_generator")
    @SequenceGenerator(name = "homework_generator", sequenceName = "seq_homeworks", allocationSize = 1)
    private Long id;

    @Embedded
    private HomeworkInstruction instruction;

    @Embedded
    private HomeworkDeadline deadline;

    @Embedded
    private GenerationYear year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "homework", cascade = CascadeType.ALL)
    private Set<StudentHomework> students = new HashSet<>();

    public Homework() {
        // Used only for spring
    }

    public Homework(HomeworkInstruction instruction, HomeworkDeadline deadline,
                    GenerationYear year) {
        this.instruction = instruction;
        this.deadline = deadline;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HomeworkInstruction getInstruction() {
        return instruction;
    }

    public void setInstruction(HomeworkInstruction instruction) {
        this.instruction = instruction;
    }

    public HomeworkDeadline getDeadline() {
        return deadline;
    }

    public void setDeadline(HomeworkDeadline deadline) {
        this.deadline = deadline;
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
        subject.getHomeworks().add(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        course.getHomeworks().add(this);
    }

    public Set<StudentHomework> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentHomework> students) {
        this.students = students;
    }

}
