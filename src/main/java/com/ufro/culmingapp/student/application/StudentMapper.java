package com.ufro.culmingapp.student.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ufro.culmingapp.shared.domain.valueobjects.GradeDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedEvaluationsDTO;

import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public List<StudentWithNestedEvaluationsDTO>
            transformInNestedObject(List<StudentWithEvaluationDTO> studentEvaluations) {

        List<StudentWithNestedEvaluationsDTO> coursesWithNestedSubjects = new ArrayList<>();

        Long id;
        Set<GradeDTO> grades = new HashSet<>();

        Long pivotId = studentEvaluations.get(0).getId();

        for (int i = 0; i < studentEvaluations.size(); i++) {

            id = studentEvaluations.get(i).getId();

            if (pivotId != id) {
                coursesWithNestedSubjects
                        .add(new StudentWithNestedEvaluationsDTO(
                                studentEvaluations.get(i - 1).getId(),
                                studentEvaluations.get(i - 1).getFirstName(),
                                studentEvaluations.get(i - 1).getLastName(),
                                grades));
                grades.clear();
            }

            grades.add(new GradeDTO(studentEvaluations.get(i).getEvaluationId(),
                    studentEvaluations.get(i).getGrade()));
            pivotId = id;
        }

        coursesWithNestedSubjects
                .add(new StudentWithNestedEvaluationsDTO(
                        studentEvaluations.get(studentEvaluations.size() - 1).getId(),
                        studentEvaluations.get(studentEvaluations.size() - 1).getFirstName(),
                        studentEvaluations.get(studentEvaluations.size() - 1).getLastName(),
                        grades));

        return coursesWithNestedSubjects;
    }

}
