package com.ufro.culmingapp.studentcourse.domain;

import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.student.domain.Student;

import javax.persistence.*;

@Entity
@Table(name = "students_courses")
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_course_generator")
    @SequenceGenerator(name = "student_course_generator", sequenceName = "seq_students_courses", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Embedded
    private GenerationYear year;

    @Column(name = "is_approved")
    private Boolean isApproved;

    public StudentCourse() {
        // Used only for spring
    }

    public StudentCourse(GenerationYear year) {
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
        student.getCourses().add(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        course.getStudents().add(this);
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

}
