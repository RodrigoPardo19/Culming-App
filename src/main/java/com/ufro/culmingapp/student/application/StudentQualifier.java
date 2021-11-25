package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.evaluation.application.EvaluationFinderService;
import com.ufro.culmingapp.evaluation.domain.Evaluation;
import com.ufro.culmingapp.evaluation.domain.exceptions.EvaluationNotFound;
import com.ufro.culmingapp.shared.domain.valueobjects.Grade;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.StudentEvaluation;
import com.ufro.culmingapp.student.domain.StudentEvaluationRepository;
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
}
