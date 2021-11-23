package com.ufro.culmingapp.student.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedEvaluationsDTO;
import com.ufro.culmingapp.student.domain.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentFinderService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    public List<StudentWithNestedEvaluationsDTO>
            getEvaluationsOfStudentsOfASubjectInACourse(Integer courseId, Integer subjectId) {
        Optional<List<StudentWithEvaluationDTO>> studentsEvaluations = repository
                .fetchEvaluationsOfStudentsOfASubjectInACourse(courseId, subjectId);
        if(studentsEvaluations.get().isEmpty()) {
            return new ArrayList<>();
        } 
        return mapper.transformInNestedObject(studentsEvaluations.get());
    }

    public List<StudentWithNestedEvaluationsDTO>
            getWorkshopsOfStudentsOfASubjectInACourse(Integer courseId, Integer subjectId) {
        Optional<List<StudentWithEvaluationDTO>> studentsWorkshops = repository
                .fetchWorkshopsOfStudentsOfASubjectInACourse(courseId, subjectId);
        if(studentsWorkshops.get().isEmpty()) {
            return new ArrayList<>();
        } 
        return mapper.transformInNestedObject(studentsWorkshops.get());
    }


}
