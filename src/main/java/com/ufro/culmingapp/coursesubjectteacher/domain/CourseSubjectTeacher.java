package com.ufro.culmingapp.coursesubjectteacher.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.teacher.domain.Teacher;

@Entity
@Table(name = "courses_subjects_teachers")
public class CourseSubjectTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Embedded
    private GenerationYear year;

    public CourseSubjectTeacher() {
        // Used only for spring
    }

    public CourseSubjectTeacher(GenerationYear year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        course.getSubjects().add(this);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.getCourses().add(this);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getSubjects().add(this);
    }

    public GenerationYear getYear() {
        return year;
    }

    public void setYear(GenerationYear year) {
        this.year = year;
    }

}
