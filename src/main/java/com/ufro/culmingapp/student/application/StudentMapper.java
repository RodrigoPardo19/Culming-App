package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.assistance.application.DTOs.AssistancesStatusDTO;
import com.ufro.culmingapp.homework.application.DTOs.HomeworkStatusDTO;
import com.ufro.culmingapp.shared.domain.valueobjects.GradeDTO;
import com.ufro.culmingapp.student.application.DTOs.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentMapper {

    public List<StudentWithNestedEvaluationsDTO>
    transformInNestedObject(List<StudentWithEvaluationDTO> studentEvaluations) {

        List<StudentWithNestedEvaluationsDTO> studentsWithNestedEvaluations = new ArrayList<>();

        Long id;
        List<GradeDTO> grades = new ArrayList<>();

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
        List<HomeworkStatusDTO> states = new ArrayList<>();

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
        List<AssistancesStatusDTO> states = new ArrayList<>();

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

    public StudentWithNestedEvaluationsDTO
    transformInStudentWithNesteEvaluations(List<StudentWithEvaluationDTO> studentEvaluations) {
        final int STUDENT_INDEX = 0;
        List<GradeDTO> grades = studentEvaluations.stream()
                .map(s -> new GradeDTO(s.getEvaluationId(), s.getGrade()))
                .collect(Collectors.toList());
        Long id = studentEvaluations.get(STUDENT_INDEX).getId();
        String firstName = studentEvaluations.get(STUDENT_INDEX).getFirstName();
        String lastName = studentEvaluations.get(STUDENT_INDEX).getLastName();
        return new StudentWithNestedEvaluationsDTO(id, firstName, lastName, grades);
    }

    public StudentWithNestedHomeworksDTO
    transformInStudentWithNesteHomeworks(List<StudentWithHomeworkDTO> studentHomeworks) {
        final int STUDENT_INDEX = 0;
        List<HomeworkStatusDTO> states = studentHomeworks.stream()
                .map(s -> new HomeworkStatusDTO(s.getHomeworkId(), s.getHomeworkStatus()))
                .collect(Collectors.toList());
        Long id = studentHomeworks.get(STUDENT_INDEX).getId();
        String firstName = studentHomeworks.get(STUDENT_INDEX).getFirstName();
        String lastName = studentHomeworks.get(STUDENT_INDEX).getLastName();
        return new StudentWithNestedHomeworksDTO(id, firstName, lastName, states);
    }
}
