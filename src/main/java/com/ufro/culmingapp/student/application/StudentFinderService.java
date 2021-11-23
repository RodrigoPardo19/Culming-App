package com.ufro.culmingapp.student.application;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedEvaluationsDTO;
import com.ufro.culmingapp.student.domain.StudentRepository;
import com.ufro.culmingapp.studentcourse.domain.StudentCourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentFinderService {

    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    @Autowired
    private CourseFinderService courseFinder;

    @Autowired
    public StudentFinderService(StudentRepository repository) {
        this.repository = repository;
    }

    public Set<StudentCourse> getAllStudentsFromCourse(Integer courseId, Integer subjectId) {
        Course course = courseFinder.findById(courseId);
        // To-Do Objetener los grades del estudiante whwere subject id =
        Set<StudentCourse> students = course.getStudents();
        return students;
    }

    public List<StudentWithNestedEvaluationsDTO>
            getEvaluationsOfStudentsOfASubjectInACourse(Integer courseId, Integer subjectId) {
        Optional<List<StudentWithEvaluationDTO>> studentsEvaluations = repository
                .fetchEvaluationsOfStudentsOfASubjectInACourse(courseId, subjectId);
        return mapper.transformInNestedObject(studentsEvaluations.get());
    }

    public List<StudentWithNestedEvaluationsDTO>
            getWorkshopsOfStudentsOfASubjectInACourse(Integer courseId, Integer subjectId) {
        Optional<List<StudentWithEvaluationDTO>> studentsEvaluations = repository
                .fetchWorkshopsOfStudentsOfASubjectInACourse(courseId, subjectId);
        return mapper.transformInNestedObject(studentsEvaluations.get());
    }

}
