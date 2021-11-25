package com.ufro.culmingapp.evaluation.domain.exceptions;

public class EvaluationNotFound extends Exception {
    
    public EvaluationNotFound(Long id) {
        super("Evaluation has not been found " + id);
    }
}
