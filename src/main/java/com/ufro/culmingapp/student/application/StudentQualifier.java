package com.ufro.culmingapp.student.application;

import java.util.Optional;

import com.ufro.culmingapp.evaluation.application.EvaluationFinderService;
import com.ufro.culmingapp.evaluation.domain.Evaluation;
import com.ufro.culmingapp.evaluation.domain.exceptions.EvaluationNotFound;
import com.ufro.culmingapp.shared.domain.valueobjects.Grade;
import com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.StudentEvaluation;
import com.ufro.culmingapp.student.domain.StudentEvaluationRepository;
import com.ufro.culmingapp.student.domain.exceptions.StudentEvaluationNotFound;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentQualifier {

    @Autowired
    private StudentEvaluationRepository repository;
    
    @Autowired
    private StudentFinderService studentFinder;

    @Autowired
    private EvaluationFinderService evaluationFinder; 

    public void rate(Long studentId, Long evaluationId, Grade grade) throws StudentNotFound,
     EvaluationNotFound {

        Student student = studentFinder.findById(studentId);
        Evaluation evaluation = evaluationFinder.findById(evaluationId); 

        StudentEvaluation studentEvaluation = new StudentEvaluation();

        studentEvaluation.setStudent(student);
        studentEvaluation.setEvaluation(evaluation);
        studentEvaluation.setGrade(grade);

        repository.save(studentEvaluation);
    }

    public StudentWithEvaluationDTO changeGrade(Long studentId, Long evaluationId, Grade grade) throws StudentNotFound,
     EvaluationNotFound, StudentEvaluationNotFound {

        Student student = studentFinder.findById(studentId);
        Evaluation evaluation = evaluationFinder.findById(evaluationId); 

        Optional<StudentEvaluation> studentEvaluation = repository.findByStudentIdAndEvaluationId(studentId, evaluationId);

        if(!studentEvaluation.isPresent()) {
            throw new StudentEvaluationNotFound(studentId, evaluationId);
        }
        StudentEvaluation evaluationChanged = studentEvaluation.get();
        evaluationChanged.setGrade(grade);

        repository.save(evaluationChanged);

        String firstName = student.getFullName().getFirstName(); 
        String lastName = student.getFullName().getLastName(); 
        Double gradeChanged = grade.getGrade();

        return new StudentWithEvaluationDTO(studentId, firstName, lastName, evaluationId, gradeChanged);
    }
}
