package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.student.application.DTOs.StudentWithAssistanceDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedAssistancesDTO;
import com.ufro.culmingapp.student.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentAssistanceFinder {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    public List<StudentWithNestedAssistancesDTO>
    getAssistancesOfStudentsOfASubjectInACourse(Integer courseId, Integer subjectId, Integer month, Integer year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        Optional<List<StudentWithAssistanceDTO>> studentsAssistances = repository
                .fetchAssistancesOfStudentsOfASubjectInACourse(courseId, subjectId, start, end);
        if (studentsAssistances.get().isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.transformInStudentsWithNestedAssistances(studentsAssistances.get());
    }

    public StudentWithNestedAssistancesDTO getAssistancesOfStudentOfASubjectInACourse(Integer courseId,
                                                                                      Integer subjectId,
                                                                                      Long studentId,
                                                                                      Integer month,
                                                                                      Integer year) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Optional<List<StudentWithAssistanceDTO>> studentAssistances = repository
                .fetchAssistancesOfStudentOfASubjectInACourse(courseId, subjectId, studentId, start, end);
        if (studentAssistances.get().isEmpty()) {
            return new StudentWithNestedAssistancesDTO();
        }
        return mapper.transformInStudentWithNestedAssistance(studentAssistances.get());
    }
}
