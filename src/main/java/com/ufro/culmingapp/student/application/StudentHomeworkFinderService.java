package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.student.application.DTOs.StudentWithHomeworkDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedHomeworksDTO;
import com.ufro.culmingapp.student.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentHomeworkFinderService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    public List<StudentWithNestedHomeworksDTO>
    getHomeworksOfStudentsOfASubjectInACourse(Integer courseId, Integer subjectId, Integer month, Integer year) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Optional<List<StudentWithHomeworkDTO>> studentsHomeworks = repository
                .fetchHomeworksOfStudentsOfASubjectInACourse(courseId, subjectId, start, end);

        if (studentsHomeworks.get().isEmpty()) {
            return new ArrayList<>();
        }

        return mapper.transformInStudentsWithNestedHomeworks(studentsHomeworks.get());
    }

    public StudentWithNestedHomeworksDTO getHomeworksOfStudentOfASubjectInACourse(Integer courseId,
                                                                                  Integer subjectId,
                                                                                  Long studentId, Integer month,
                                                                                  Integer year) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Optional<List<StudentWithHomeworkDTO>> studentsEvaluations = repository
                .fetchHomeworksOfAStudentOfASubjectInACourse(courseId, subjectId, studentId, start, end);

        if (studentsEvaluations.isEmpty()) {
            return new StudentWithNestedHomeworksDTO();
        }
        return mapper.transformInStudentWithNesteHomeworks(studentsEvaluations.get());
    }
}
