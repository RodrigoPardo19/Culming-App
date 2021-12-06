package com.ufro.culmingapp.teacher.application;

import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.coursesubjectteacher.domain.CourseSubjectTeacher;
import com.ufro.culmingapp.coursesubjectteacher.domain.CourseSubjectTeacherRepository;
import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.subject.application.SubjectFinderService;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterSubject {

    @Autowired
    private TeacherFinderService teacherFinder;

    @Autowired
    private SubjectFinderService subjectFinder;

    @Autowired
    private CourseFinderService courseFinder;

    @Autowired
    private CourseSubjectTeacherRepository repository;

    public void register(Long teacherId, Integer subjectId, Set<Integer> courseIds, GenerationYear year) throws
            TeacherNotFound, SubjectNotFound, CourseNotFound {

        Teacher teacher = teacherFinder.findById(teacherId);
        Subject subject = subjectFinder.findById(subjectId);

        Set<Course> courses = checkIfCoursesExist(courseIds);

        courses.forEach(course -> {
            CourseSubjectTeacher courseSubjectTeacher = new CourseSubjectTeacher(year);
            courseSubjectTeacher.setTeacher(teacher);
            courseSubjectTeacher.setSubject(subject);
            courseSubjectTeacher.setCourse(course);
            repository.save(courseSubjectTeacher);
        });
    }

    private Set<Course> checkIfCoursesExist(Set<Integer> coursesId) throws CourseNotFound {
        Set<Course> courses = new HashSet<>();
        for (Integer id : coursesId) {
            Course course = courseFinder.findById(id);
            courses.add(course);
        }
        return courses;
    }
}
