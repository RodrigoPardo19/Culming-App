package com.ufro.culmingapp.evaluationtype.domain.exceptions;

public class EvaluationTypeNotFound extends Exception {

    public EvaluationTypeNotFound(Integer id) {
        super("Type of evaluation has not been found " + id);
    }
    
}
