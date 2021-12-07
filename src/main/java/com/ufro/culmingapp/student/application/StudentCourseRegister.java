package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import com.ufro.culmingapp.studentcourse.domain.StudentCourse;
import com.ufro.culmingapp.studentcourse.domain.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseRegister {

    @Autowired
    private StudentCourseRepository repository;

    @Autowired
    private StudentFinderService studentFinder;

    @Autowired
    private CourseFinderService courseFinder;

    public void register(Long studentId, Integer courseId, GenerationYear year) throws StudentNotFound, CourseNotFound {
        Student student = studentFinder.findById(studentId);
        Course course = courseFinder.findById(courseId);
        StudentCourse studentCourse = new StudentCourse(year);
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        repository.save(studentCourse);
    }
}
