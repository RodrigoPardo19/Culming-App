package com.ufro.culmingapp.student.domain.exceptions;

public class StudentEvaluationNotFound extends Exception {

    public StudentEvaluationNotFound(Long studentId, Long evaluationId) {
        super("Student's evaluation has not been found, please check the studentId: " 
        + studentId + " or evaluationId: " + evaluationId);
    }

}
