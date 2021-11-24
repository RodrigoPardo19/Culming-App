package com.ufro.culmingapp.student.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ufro.culmingapp.assistance.application.DTOs.AssistancesStatusDTO;
import com.ufro.culmingapp.homework.application.DTOs.HomeworkStatusDTO;
import com.ufro.culmingapp.shared.domain.valueobjects.GradeDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithAssistanceDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithHomeworkDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedAssistancesDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedEvaluationsDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedHomeworksDTO;

import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public List<StudentWithNestedEvaluationsDTO>
            transformInNestedObject(List<StudentWithEvaluationDTO> studentEvaluations) {

        List<StudentWithNestedEvaluationsDTO> studentsWithNestedEvaluations = new ArrayList<>();

        Long id;
        Set<GradeDTO> grades = new HashSet<>();

        Long pivotId = studentEvaluations.get(0).getId();

        for (int i = 0; i < studentEvaluations.size(); i++) {

            id = studentEvaluations.get(i).getId();

            if (pivotId != id) {
                studentsWithNestedEvaluations
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

        studentsWithNestedEvaluations
                .add(new StudentWithNestedEvaluationsDTO(
                        studentEvaluations.get(studentEvaluations.size() - 1).getId(),
                        studentEvaluations.get(studentEvaluations.size() - 1).getFirstName(),
                        studentEvaluations.get(studentEvaluations.size() - 1).getLastName(),
                        grades));

        return studentsWithNestedEvaluations;
    }

    public List<StudentWithNestedHomeworksDTO>
            transformInStudentsWithNestedHomeworks(List<StudentWithHomeworkDTO> studentHomeworks) {

        List<StudentWithNestedHomeworksDTO> studentsWithNestedHomeworks = new ArrayList<>();

        Long id;
        Set<HomeworkStatusDTO> states = new HashSet<>();

        Long pivotId = studentHomeworks.get(0).getId();

        for (int i = 0; i < studentHomeworks.size(); i++) {

            id = studentHomeworks.get(i).getId();

            if (pivotId != id) {
                studentsWithNestedHomeworks
                        .add(new StudentWithNestedHomeworksDTO(
                                studentHomeworks.get(i - 1).getId(),
                                studentHomeworks.get(i - 1).getFirstName(),
                                studentHomeworks.get(i - 1).getLastName(),
                                states));
                states.clear();
            }

            states.add(new HomeworkStatusDTO(studentHomeworks.get(i).getHomeworkId(),
                    studentHomeworks.get(i).getHomeworkStatus()));
            pivotId = id;
        }

        studentsWithNestedHomeworks
                .add(new StudentWithNestedHomeworksDTO(
                        studentHomeworks.get(studentHomeworks.size() - 1).getId(),
                        studentHomeworks.get(studentHomeworks.size() - 1).getFirstName(),
                        studentHomeworks.get(studentHomeworks.size() - 1).getLastName(),
                        states));

        return studentsWithNestedHomeworks;
    }

    public List<StudentWithNestedAssistancesDTO>
            transformInStudentsWithNestedAssistances(List<StudentWithAssistanceDTO> studentAssistances) {

        List<StudentWithNestedAssistancesDTO> studentsWithNestedAssistances = new ArrayList<>();

        Long id;
        Set<AssistancesStatusDTO> states = new HashSet<>();

        Long pivotId = studentAssistances.get(0).getId();

        for (int i = 0; i < studentAssistances.size(); i++) {

            id = studentAssistances.get(i).getId();

            if (pivotId != id) {
                studentsWithNestedAssistances
                        .add(new StudentWithNestedAssistancesDTO(
                                studentAssistances.get(i - 1).getId(),
                                studentAssistances.get(i - 1).getFirstName(),
                                studentAssistances.get(i - 1).getLastName(),
                                states));
                states.clear();
            }

            states.add(new AssistancesStatusDTO(studentAssistances.get(i).getAssistanceId(),
                    studentAssistances.get(i).isPresent()));
            pivotId = id;
        }

        studentsWithNestedAssistances
                .add(new StudentWithNestedAssistancesDTO(
                        studentAssistances.get(studentAssistances.size() - 1).getId(),
                        studentAssistances.get(studentAssistances.size() - 1).getFirstName(),
                        studentAssistances.get(studentAssistances.size() - 1).getLastName(),
                        states));

        return studentsWithNestedAssistances;
    }
}
